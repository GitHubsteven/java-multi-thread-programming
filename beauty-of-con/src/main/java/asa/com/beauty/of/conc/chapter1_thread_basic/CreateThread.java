package asa.com.beauty.of.conc.chapter1_thread_basic;

import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 9:50 2018/11/16.
 */
public class CreateThread {
    //three way to create thread.


    public static class Runner_1 implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello, runnable!");
        }
    }

    public static class Runner_2 extends Thread {
        Runner_2(String name) {
            setName(name);
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello, thread!");
        }
    }

    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            return "hello, callable";
        }
    }

    public static void main(String[] args) {
        Thread thread_1 = new Thread(new Runner_1(), "runnable");
        Thread thread_2 = new Runner_2("thread");
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());

        thread_1.start();
        thread_2.start();
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> submit = executorService.submit(futureTask, "result");
        Future<String> submit_dir = executorService.submit(new CallerTask());
        try {
            System.out.println("=========");
            System.out.println(submit_dir.get());
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

//    public static class Runner_3 extends
}