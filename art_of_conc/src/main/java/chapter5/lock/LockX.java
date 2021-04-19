package chapter5.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/4/14 11:14
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/4/14 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class LockX {
    private final Lock lock = new ReentrantLock();
    int value;

    public void addOne() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();
        }
    }
}

