package basic;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 终止线程一般通过interrupt和一般的共享标识位来终止线程，stop函数被弃用了
 * @Date: Created at 9:33 2018/11/7.
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread thread_one = new Thread(one, "thread one");
        thread_one.start();
        TimeUnit.SECONDS.sleep(1);
        thread_one.interrupt();     //通过interrupt的方法来终止线程

        Runner two = new Runner();
        Thread thread_two = new Thread(two, "thread two");
        thread_two.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();               //通过标志位来终止线程

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
