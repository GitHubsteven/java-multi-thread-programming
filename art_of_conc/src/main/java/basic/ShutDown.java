package basic;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 9:33 2018/11/7.
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread thread_one = new Thread(one, "thread one");
        thread_one.start();
        TimeUnit.SECONDS.sleep(1);
        thread_one.interrupt();

        Runner two = new Runner();
        Thread thread_two = new Thread(two, "thread two");
        thread_two.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }


    public static class Runner implements Runnable {
        private long i;                                    //成员变量
        private volatile boolean on = true;             //volatile 多个线程之间通信

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + " count: " + i);
        }

        public void cancel() {
            this.on = false;
        }
    }
}
