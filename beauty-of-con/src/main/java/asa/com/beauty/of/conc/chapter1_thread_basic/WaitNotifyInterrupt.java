package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 线程阻塞后被中断会抛出异常，这是基于初步原始的设计
 * @Date: Created at 10:49 2018/11/16.
 */
public class WaitNotifyInterrupt {
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("-----begin----");
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println("------end-----");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Thread.sleep(1000);

        System.out.println("---begin to interrupt thread");
        thread.interrupt();
        System.out.println("---end interrupt the thread");
    }
}