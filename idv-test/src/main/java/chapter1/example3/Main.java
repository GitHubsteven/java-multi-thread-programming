package chapter1.example3;

import java.util.concurrent.CountDownLatch;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class Main {
    CountDownLatch start = new CountDownLatch(10);

    public static void main(String[] args) {

        commonStartThread();

    }

    private static void commonStartThread() {
        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread("thread" + i);
            thread.start();
        }
    }

}
