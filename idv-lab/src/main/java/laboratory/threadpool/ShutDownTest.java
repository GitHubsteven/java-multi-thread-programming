package laboratory.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/6/11 10:31
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/6/11 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class ShutDownTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0,
                TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() < start + 1000) {
                    business();
                    break;
                }
                business();
            });
        }

        executor.shutdown();
        System.out.printf("%s:- shutdown\n", LocalDateTime.now().toString());
        try {
            executor.awaitTermination(15, TimeUnit.SECONDS);
            System.out.printf("%s:- main thread end!\n", LocalDateTime.now().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void business() {
        System.out.printf("%s:- thread:%s start...%n", LocalDateTime.now().toString(), Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s:- thread:%s end...%n", LocalDateTime.now().toString(), Thread.currentThread().getName());
    }
}

