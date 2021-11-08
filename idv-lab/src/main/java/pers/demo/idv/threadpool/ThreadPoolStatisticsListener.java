package pers.demo.idv.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/6/3 15:06
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/6/3 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class ThreadPoolStatisticsListener {
    protected static ExecutorService es = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(
                    100000
            ));

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            es.execute(() -> {
                System.out.print(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);
        while (true) {
            System.out.println();
            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队任务数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成任务数：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总任务数：" + taskCount);

            Thread.sleep(3000);
        }
    }
}

