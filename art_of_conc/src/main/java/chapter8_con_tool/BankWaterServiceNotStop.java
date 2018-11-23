package chapter8_con_tool;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 执行顺序：所有子程序屏障前 ->barrierAction ->屏障后代码
 * @Date: Created at 10:58 2018/11/23.
 */
public class BankWaterServiceNotStop implements Runnable {
    private final static int THREAD_SIZE = 4;

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_SIZE, this);
    /**
     * 只有4个线程，所以启动4个线程，这里有个问题，即使有N个sheet，也不可能就设置N个线程
     */
    private Executor executor = Executors.newFixedThreadPool(THREAD_SIZE);
    /**
     * 保存每个sheet的计算的银行流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();


    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            System.out.println(String.format("thread[%s] count value is: %d", sheet.getKey(), sheet.getValue()));
            result += sheet.getValue();
        }
        sheetBankWaterCount.putIfAbsent("result", result);
        System.out.println(result);
    }

    public void count() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            executor.execute(() -> {
                //计算流水过程忽略
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int value = random.nextInt(10);
                sheetBankWaterCount.put(Thread.currentThread().getName(), value);
                //计算完成，插入屏障，所以，这个屏障插入在那里是个很有讲究的问题
                try {
                    cyclicBarrier.await();    //等，直到所有的屏障点到了才结束，这里，你可以放在开始，一般放在程序运行完。
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        BankWaterServiceNotStop bankWaterService = new BankWaterServiceNotStop();
        bankWaterService.count();
    }
}