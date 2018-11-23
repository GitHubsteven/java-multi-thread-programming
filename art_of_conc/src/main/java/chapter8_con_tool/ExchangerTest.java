package chapter8_con_tool;

import java.util.concurrent.*;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: exgr如果交互一次后，数据会被释放？
 * @Date: Created at 13:04 2018/11/23.
 */
public class ExchangerTest {
    /**
     * 静态类
     */
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            String bank_water_a = "bank water A";
            try {
                exgr.exchange(bank_water_a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            try {
                String b = "bank water B";
                String a = exgr.exchange("1");
                System.out.println("a和b的数据是否一致：" + a.equalsIgnoreCase(b) + "a value: " + a + " b value: " + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
                String value = exgr.exchange("2", 2, TimeUnit.SECONDS);
                System.out.println("交缓过来的值是 " + value);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }
}