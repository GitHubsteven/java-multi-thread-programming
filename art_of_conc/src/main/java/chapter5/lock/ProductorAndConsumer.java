package chapter5.lock;

import util.SleepUtils;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 13:48 2018/11/26.
 */
public class ProductorAndConsumer {
    private final static NonReentrantLock lock = new NonReentrantLock();
    private final static Condition notFull = lock.newCondition();
    private final static Condition notEmpty = lock.newCondition();

    private final static Queue<String> queue = new LinkedBlockingDeque<>();
    private final static int queueSize = 10;

    public static void main(String[] args) {
        Thread product = new Thread(() -> {
            lock.lock();
            //wait if queue is full;
            try {
                while (queue.size() == queueSize) {
                    notFull.await();
                }
                queue.add("ele");
                System.out.println("------------product a ele");
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    notEmpty.await();
                }
                String ele = queue.poll();
                System.out.println("------------consume a ele");
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        product.start();
        SleepUtils.sencond(2);
        consumer.start();
    }
}