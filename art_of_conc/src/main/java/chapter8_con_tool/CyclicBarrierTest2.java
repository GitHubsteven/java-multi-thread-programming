package chapter8_con_tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrierAction），
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
 * @Date: Created at 10:53 2018/11/23.
 */
public class CyclicBarrierTest2 {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("barrierAction"));

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        extract(cyclicBarrier);

        cyclicBarrier.await();
        System.out.println("main_thread");
    }

    static void extract(CyclicBarrier cyclicBarrier) {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("sub_thread");
        }).start();
    }
}