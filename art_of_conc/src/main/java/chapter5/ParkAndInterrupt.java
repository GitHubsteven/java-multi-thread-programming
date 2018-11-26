package chapter5;

import util.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:16 2018/11/26.
 */
public class ParkAndInterrupt {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("sub thread parks");
            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
//            LockSupport.park();
            System.out.println("sub thread unparks");
        });

        thread.start();
        SleepUtils.sencond(1);
        System.out.println("main thread begin unpark!");

        thread.interrupt();
//        LockSupport.unpark(thread);
    }
}