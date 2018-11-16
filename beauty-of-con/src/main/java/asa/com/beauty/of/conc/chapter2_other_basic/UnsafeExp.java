package asa.com.beauty.of.conc.chapter2_other_basic;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:48 2018/11/16.
 */
public class UnsafeExp {
    private int value;

    public static void main(String[] args) {
        try {
            Unsafe unsafe = Unsafe.getUnsafe();
            System.out.println(unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value")));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}