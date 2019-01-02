package chapter10_executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:30 2018/11/23.
 */
public class BasicExecutorTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        List<Runnable> runnables = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            runnables.add(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "start----------");
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + "end-------------");
            });
        }
        for (int i = 0; i < runnables.size(); i++) {
            executorService.execute(runnables.get(i));
        }
        System.out.println("main thread--------------------end!");
        executorService.shutdown();
    }
}