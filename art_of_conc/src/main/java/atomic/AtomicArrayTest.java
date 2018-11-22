package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:03 2018/11/22.
 */
public class AtomicArrayTest {
    static int[] value = new int[]{1, 2, 3};
    //这里的数组是复制进去的，而不是直接地址引用。
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println("ai.get(0) = " + ai.get(0));
        System.out.println("value[0] = " + value[0]);
    }
}