package basic;

import util.SleepUtils;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 22:29 2018/11/6.
 */
public class ThreadStatus {
    public static void main(String[] args) {
        //create thread
        Thread test_thread = new Thread(new TestThread(), "testThread!");
        //thread enter runnable or ready state
        test_thread.start();
        SleepUtils.sencond(1);
        try {
            test_thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class TestThread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread: " + Thread.currentThread().getName() + "start");
            while (true) {
                System.out.println("working....");
            }
        }
    }
}