package chapter4.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/16
 * @Time: 15:26
 * @Description:
 * @version: 1.0.0
 */
public class CountDownlatchTest2 {
    public static void main(String[] args) throws InterruptedException {
//        runThreadNoCuntDownLatch();  96-113
//        runThreadCuntDownLatch();     //130-170
//        customTest();      //193 - 233
    }

    private static void runThreadNoCuntDownLatch() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

            }) {
            }.start();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - start));
    }

    private static void runThreadCuntDownLatch() throws InterruptedException {
        int threadNum = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new countThread("thread" + i, countDownLatch).start();
        }
        countDownLatch.await();//一直在阻塞 等待线程执行完后
        System.out.println("-----------打log测试-----------");//这个打log可以看的出来是等线程执行完后再执行下面的代码
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - start));

    }

    @Deprecated
    private static void customTest() throws InterruptedException {
        int threadNum = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        final CountDownLatch startSignal = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {

                countDownLatch.countDown();
            }) {
            }.start();
        }
        startSignal.countDown();
        countDownLatch.await();//一直在阻塞 等待线程执行完后
        System.out.println("-----------打log测试-----------");//这个打log可以看的出来是等线程执行完后再执行下面的代码
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - start));

    }

    static class countThread extends Thread {
        static double runTime = 0;
        static int count = 0;
        private final CountDownLatch countDownLatch;

        public countThread(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            int time = (int) (Math.random() * 10);
            try {
                TimeUnit.MILLISECONDS.sleep(time);
                count++;
                runTime = runTime + time;
                if (count == 999) {
                    System.out.println("total time: " + runTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            countDownLatch.countDown();
        }
    }
}