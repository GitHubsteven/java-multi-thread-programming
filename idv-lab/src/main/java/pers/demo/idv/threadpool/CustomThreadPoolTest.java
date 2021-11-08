package pers.demo.idv.threadpool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/6/3 10:38
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/6/3 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class CustomThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        List<Runnable> runnables = new ArrayList<>(100);
        System.out.println("available processors: " + Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100; i++) {
            runnables.add(new MyRunnable("" + i));
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 0, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>());
        runnables.forEach(threadPoolExecutor::execute);
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MINUTES);
    }


    static class MyRunnable implements Runnable {
        private final String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.printf("task:%s start at:%s\n", name, LocalDateTime.now().toString());
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("task:%s stopped\n", name);
        }
    }
}

