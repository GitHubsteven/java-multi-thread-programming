package executor.lab;

import model.Person;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 17:13 2018/11/2.
 */
public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        scheduleExecutorTest();
    }


    /**
     * scheduleExecutor + callable 初探
     *
     * @throws InterruptedException
     */
    private static void scheduleExecutorTest() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        ScheduledFuture<Person> schedule = scheduledExecutorService.schedule(new CallableImpl1(), 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("hello,world"), 0, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> exception_test = scheduledExecutorService.scheduleAtFixedRate(() -> {
            throw new RuntimeException("exception test");           //callable如果没被获取结果的话，那么就不会抛出异常，难道逻辑是如果不需要获取结果，会自动忽略异常？
//            System.out.println("5");
        }, 0, 3, TimeUnit.SECONDS);

        try {
            exception_test.cancel(false);
            exception_test.isCancelled();
            exception_test.isDone();
//            exception_test

            exception_test.get();//没有完成导致堵塞，如果完成，要么正常返回，要么抛出异常结束，要么被取消结束
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void threadPoolExecutorTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ConcurrentHashMap<Object,Future<String>> taskCache = new ConcurrentHashMap<>();
//        taskCache.putIfAbsent();
//        taskCache.remove();
//        taskCache.
        HashMap<String,Object> cache = new HashMap<>();
    }
}