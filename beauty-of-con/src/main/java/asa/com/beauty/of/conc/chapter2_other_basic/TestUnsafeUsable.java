package asa.com.beauty.of.conc.chapter2_other_basic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 15:14 2018/11/16.
 */
public class TestUnsafeUsable {
    private static final Unsafe unsafe;

    private static final long stateOffSet;
    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffSet = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        TestUnsafeUsable test = new TestUnsafeUsable();
        boolean isSuccess = unsafe.compareAndSwapInt(test, stateOffSet, 0, 1);
        System.out.println(isSuccess + " new value is : " + test.state);
    }
}