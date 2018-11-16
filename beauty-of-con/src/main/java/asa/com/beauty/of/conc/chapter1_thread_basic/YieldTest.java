package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 12:29 2018/11/16.
 */
public class YieldTest implements Runnable {
    YieldTest() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        String currentName = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(String.format("thread[%s] yield at number: %d", currentName, i));
                //让出时间片，供其他线程调度吗？
                Thread.yield();      //时间片好随机啊
            }
        }
        System.out.println(String.format("thread[%s] is over", currentName));
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}