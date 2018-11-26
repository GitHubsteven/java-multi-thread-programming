package chapter5.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 15:41 2018/11/26.
 */
public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    public void fair() {
        testLock(fairLock);
    }

    private void testLock(Lock lock) {
        for (int i = 0; i < 5; i++) {
            Job job = new Job(lock);
            job.start();
            try {
                job.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unfair() {
        testLock(unfairLock); //150,119,199,188,15834
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        public void run() {
//            lock
//            System.out.println("-------------");
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            ArrayList<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    public static void main(String[] args) {
        FairAndUnfairTest test = new FairAndUnfairTest();
        long start = System.currentTimeMillis();
        test.unfair();
        long end = System.currentTimeMillis();
        System.out.println("time(ms): " + (end - start));
    }
}