package executor.lab;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 16:34 2018/11/2.
 */
public class NoExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
        long start = new Date().getTime();
        threadMass(10000);
        System.out.println("spend(ms): " + (new Date().getTime() - start));
    }

    public static void threadMass(int number) {
        for (int i = 0; i < number; i++) {
            new CusThread(i + "").start();
        }
    }


    static class CusThread extends Thread {
        CusThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String currentThread = Thread.currentThread().getName();
            System.out.println("start thread: " + currentThread);
            try {
                System.out.println("thread: " + currentThread + "sleep 2s");
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread: " + currentThread + "run end");

        }
    }
}