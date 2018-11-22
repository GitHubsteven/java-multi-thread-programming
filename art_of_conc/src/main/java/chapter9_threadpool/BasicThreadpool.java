package chapter9_threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import util.SleepUtils;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 9:47 2018/11/22.
 */
public class BasicThreadpool {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<Runnable> workers = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor pool_1 = new ThreadPoolExecutor(4, 10, 0L, TimeUnit.SECONDS,
                workers, new ThreadFactoryBuilder().build(), new CusAbortStrategy());
        ThreadPoolExecutor pool_2 = new ThreadPoolExecutor(4, 10, 0L, TimeUnit.SECONDS, workers);
        ThreadPoolExecutor pool_3 = new ThreadPoolExecutor(4, 10, 0L, TimeUnit.SECONDS,
                workers, new ThreadFactoryBuilder().build(), new ThreadPoolExecutor.AbortPolicy());

        pool_1.execute(() -> {
            System.out.println("hello, thread pool!");
            SleepUtils.sencond(10);
            System.out.println("goodbye, thread pool!");
        });
        System.out.println("hello, main");
        System.out.println("ActiveCount: " + pool_1.getActiveCount());
        pool_1.shutdown();

    }
}