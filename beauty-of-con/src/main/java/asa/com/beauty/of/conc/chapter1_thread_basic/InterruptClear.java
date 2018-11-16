package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 13:11 2018/11/16.
 */
public class InterruptClear {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //中断标志为 true 时会退出循环，并且清除中断标志.
            while (!Thread.interrupted()) {

            }
            System.out.println(String.format("sub thread isInterrupted:[%s] ",
                    Thread.currentThread().isInterrupted()));
            //should be true, but false for it is cleared.
        }, "interrupt_clear");

        thread.start();
        thread.interrupt();
        thread.join();
        System.out.println("main thread end!");
    }
}