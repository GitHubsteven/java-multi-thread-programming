package chapter8_con_tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数
 * 量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
 * @Date: Created at 10:38 2018/11/23.
 */
public class CyclicBarrierTest {
    /*
    * 如果把new CyclicBarrier(2)修改成new CyclicBarrier(3)，则主线程和子线程会永远等待，
      因为没有第三个线程执行await方法，即没有第三个线程到达屏障，所以之前到达屏障的两个
    线程都不会继续执行
    */
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrierTest2.extract(cyclicBarrier);
        cyclicBarrier.await();
        System.out.println("main_thread");
    }
}