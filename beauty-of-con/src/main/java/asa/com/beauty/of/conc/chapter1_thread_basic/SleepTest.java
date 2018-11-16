package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 调用sleep会释放cpu的执行权，但是锁等资源依然没有被释放，拥有锁，无cpu执行，那么这是超时等待吗？
 * 调用interrupt会导致异常
 * @Date: Created at 11:15 2018/11/16.
 */
public class SleepTest {
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (obj) {
                    System.out.println("thread get lock and start to  sleep");
                    Thread.sleep(1000);
                    System.out.println(" thread end sleep");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep And be interrupt");

        thread.start();

        System.out.println("start to interrupt the sub thread");
        thread.interrupt();
        Thread.sleep(1000 * 2);
        System.out.println("end interrupted the sub thread");
    }

}