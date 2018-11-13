package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 9:26 2018/11/13.
 */
public class BoundedQueue<T> {
    private Object[] items;
    /**
     * current insert index;
     */
    private int addIndex;
    /**
     * current remove index
     */
    private int removeIndex;
    /**
     * current number count
     */
    private int count;

    /**
     * lock
     */
    private Lock lock = new ReentrantLock();
    /**
     * not null condition, what for it creates two condition object.
     */
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];   //can not instantiated by generic.
    }

    /**
     * add a element to the queue.
     *
     * @param t element to be added
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
        /*
        queue, first in first out, index by index cursor. the node where the cursor
        is is the head node.Loop the array here by cursor.
         */
            if (++addIndex == items.length) addIndex = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * remove and return the head node of the queue.
     *
     * @return the head node.
     */
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) notEmpty.await();
            Object result = items[removeIndex];
        /*
        reset the cursor index if the cursor will be outbound
         */
            if (++removeIndex == items.length) removeIndex = 0;
            count--;
            notFull.notify();
            //noinspection unchecked
            return (T) result;
        } finally {
            lock.unlock();
        }
    }

}