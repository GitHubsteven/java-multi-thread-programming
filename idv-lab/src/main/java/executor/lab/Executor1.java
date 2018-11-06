package executor.lab;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: executor 框架初探
 * @Date: Created at 10:02 2018/11/6.
 */
public class Executor1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("string" + i);
        }
        List<List<String>> partition = Lists.partition(data, 6);
        List<Runnable> threads = new ArrayList<>();
        partition.forEach(subSet -> {
            threads.add(() -> {
                bus(subSet);
            });
        });
        threads.forEach(executorService::submit);
        executorService.shutdown(); //关闭多线程
    }

    private static void bus(List<String> params) {
        String threadName = Thread.currentThread().getName();
        System.out.println("thread: " + threadName + " start ...");
        System.out.println(params);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread : " + threadName + "end!");
    }
}