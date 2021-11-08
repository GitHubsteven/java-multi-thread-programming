package asa.com.beauty.of.conc.chapter2_other_basic;

import sun.misc.Unsafe;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 这里是无法使用的，应为unsafe是不直接提供给
 * 开发者使用的，为了安全使用，这个类只为bootstrap classLoader使用,但我们仍然有方法使用
 * 参考TestUnsafeUsable
 * @Date: Created at 14:56 2018/11/16.
 */
public class TestUnsafe {
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static final long stateOffset;

    private volatile long state = 0;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        //创建实例，并且设置state的值为1
        TestUnsafe test = new TestUnsafe();
        boolean isSuccess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(isSuccess);
        System.out.println(test.state);
    }
}