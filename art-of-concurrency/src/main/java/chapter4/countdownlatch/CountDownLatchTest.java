package chapter4.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/16
 * @Time: 14:25
 * @Description:
 * @version: 1.0.0
 */
public class CountDownLatchTest {
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Work(start, end, "name" + i)).start();
        }
        doMainThread(1);  //even if the thread.start is call, however it is still not run
        start.countDown();  // start all the waiting thread to process
        doMainThread(2); //run between the all threads
        end.await();          //wait the all thread to finish,even if the main thread business end;
        System.out.println("main thread end tip...");
    }

    private static void doMainThread(int i) throws InterruptedException {
        System.out.println("conduct main thread business start..." + i);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("conduct main thread business end..." + i);
    }


    static class Work implements Runnable {
        private CountDownLatch start;
        private CountDownLatch end;
        private String name;

        public Work(CountDownLatch start, CountDownLatch end, String name) {
            this.start = start;
            this.end = end;
            this.name = name;
        }

        @Override
        public void run() {
            try {
//                start.await();         //keep start countdownlatch keep wait, this can be delete for it's value is 1;
                dowork();
                end.countDown();     // countdownlatch count is 10, so we have to countdown it, otherwise the end countdownlatch will never end.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void dowork() throws InterruptedException {
            System.out.println(this.name + " conduct start...");
            int time = (int) (Math.random() * 10);
            time = time > 3 ? 3 : time;
            TimeUnit.SECONDS.sleep(time);
            System.out.println(String.format(this.name + "conduct end(time:%s)...", time));
        }
    }

}