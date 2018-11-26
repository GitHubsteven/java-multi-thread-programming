package chapter5.lock;

import util.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 15:24 2018/11/26.
 */
public class TwinsLockTest {
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class MyWorker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();

                    try {
                        SleepUtils.sencond(1);
                        System.out.println("thread name: " + Thread.currentThread().getName());
                        SleepUtils.sencond(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            MyWorker worker = new MyWorker();
            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0; i < 10; i++) {
            SleepUtils.sencond(1);
            System.out.println();
        }

    }
}