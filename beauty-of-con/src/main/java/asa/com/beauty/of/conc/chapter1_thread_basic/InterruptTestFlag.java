package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 13:01 2018/11/16.
 */
public class InterruptTestFlag {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        }, "interrupt_flag");
        thread.start();
        //设置中断标志位
        thread.interrupt();
        //获取中断标志位
        System.out.println("isInterrupted: " + thread.isInterrupted());
        //获取中断标志位并且重置
        System.out.println("isInterrupted: " + thread.interrupted());  //current[main] thread
        //获取中断标志位并重置
        System.out.println("isInterrupted: " + Thread.interrupted());   //current[main] thread
        //获取标志位
        System.out.println("isInterrupted: " + thread.isInterrupted());

        thread.join();
        System.out.println("main thread is over!");
    }
}