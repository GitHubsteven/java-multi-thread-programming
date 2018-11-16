package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 线程中断操作
 * @Date: Created at 12:45 2018/11/16.
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(String.format("thread [%s] hello", Thread.currentThread().getName()));
            }
        }, "interruptTest");

        thread.start();
        Thread.sleep(1000);
        System.out.println("中断子线程");
        thread.interrupt();
        thread.join();
        System.out.println("test end!");
    }
}