package chapter1;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author rongbin.xie
 * @Description:
 * @Date: Created at 16:23 2018/5/21.
 */
public class ConcurrencyAndSerial {
    private static final long count = 100001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();     // 3 ms
        serial();           //2ms
    }


    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long cost = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + cost + "ms, b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println("Serial :" + cost + "ms,b=" + b + "a = " + a);
    }

}
