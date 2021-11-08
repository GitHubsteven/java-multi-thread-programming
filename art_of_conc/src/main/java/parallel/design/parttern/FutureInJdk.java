package parallel.design.parttern;

import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 21:57 2018/11/6.
 */
public class FutureInJdk {
    public static void main(String[] args) {
        //这相当于线程了吧，client_thread
        FutureTask<String> futureTask = new FutureTask<>(new RealData("ab"));
        //建立一个client
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //client start run
        executorService.submit(futureTask);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread run after sleep 1s");
        try {
            System.out.println("thread result: " + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //一旦future.get()，如果子线程没有结束的话，那么future thread那么就占用当前主线资源，导致当前主线程阻塞，
        // 主线程需要等待future子线程的完成才执行。
        System.out.println("main thread end!");

        executorService.shutdown();
    }

    public static class RealData implements Callable<String> {
        private String param;

        public RealData(String param) {
            this.param = param;
        }

        @Override
        public String call() throws Exception {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(param);
                TimeUnit.SECONDS.sleep(2);
            }
            return sb.toString();
        }
    }
}