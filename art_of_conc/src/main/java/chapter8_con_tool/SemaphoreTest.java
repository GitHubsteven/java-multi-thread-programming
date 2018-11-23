package chapter8_con_tool;

import util.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: Semaphore可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接。假
 * 如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程
 * 并发地读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，这
 * 时我们必须控制只有10个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连
 * 接。这个时候，就可以使用Semaphore来做流量控制
 * @Date: Created at 12:44 2018/11/23.
 */
public class SemaphoreTest {
    /**
     * 线程池数
     */
    private static final int THREAD_COUNT = 30;
    private static final int SEMAPHORE_COUNT = 10;
    private static AtomicInteger count = new AtomicInteger();

    /**
     * 线程池
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(SEMAPHORE_COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("thread run" + (count.getAndDecrement()));
                    SleepUtils.sencond(5);   //在2s中内，cpu可以执行完这么简单的线程了，可以看到只有10个，
                    // 虽然这么理解有点牵强，但是梳理的例子就是这么多，暂时也就这样吧
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}