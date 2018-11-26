package chapter5.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 15:03 2018/11/26.
 */
public class ReentrantLockList {
    private ArrayList<String> array = new ArrayList<>();
    private volatile ReentrantLock lock = new ReentrantLock();

    public void add(String e) {
        lock.lock();
        try {
            array.add(e);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String e) {
        lock.lock();
        try {
            array.remove(e);
        } finally {
            lock.unlock();
        }
    }

    public String get(int index) {
        lock.lock();

        try {
            return array.get(index);
        } finally {
            lock.unlock();
        }
    }
}