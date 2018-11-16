package asa.com.beauty.of.conc.chpater4_atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 19:00 2018/11/16.
 */
public class Atomic {
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread thread_1 = new Thread(() -> {
            for (int i = 0; i < arrayOne.length; i++) {
                if (arrayOne[i] == 0) atomicLong.getAndIncrement();
            }
        }, "thread_1");

        Thread thread_2 = new Thread(() -> {
            for (int i = 0; i < arrayTwo.length; i++) {
                if (arrayOne[i] == 0) atomicLong.getAndIncrement();
            }
        }, "thread_2");
        thread_1.start();
        thread_2.start();
        //join 之前一定要执行start？白学了啊
        thread_1.join();
        thread_2.join();
        System.out.println("count of zero: " + atomicLong.get());

    }
}