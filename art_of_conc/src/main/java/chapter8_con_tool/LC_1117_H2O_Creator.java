package chapter8_con_tool;

import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/4/9 14:05
 * @description {
 * <p>
 * public H2O() {
 * <p>
 * }
 * private Semaphore h = new Semaphore(2);
 * private Semaphore o = new Semaphore(0);
 * public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
 * h.acquire(1);
 * // releaseHydrogen.run() outputs "H". Do not change or remove this line.
 * releaseHydrogen.run();
 * o.release(1);
 * }
 * <p>
 * public void oxygen(Runnable releaseOxygen) throws InterruptedException {
 * o.acquire(2);
 * // releaseOxygen.run() outputs "H". Do not change or remove this line.
 * releaseOxygen.run();
 * h.release(2);
 * }
 * }
 * @copyright COPYRIGHT © 2014 - 2021/4/9 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class LC_1117_H2O_Creator {

    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        releaseOxygen.run();
        h.release(2);
    }

    static class ReleaseHydrogen implements Runnable {
        @Override
        public void run() {
            System.out.println("H");
        }
    }

    static class releaseOxygen implements Runnable {
        @Override
        public void run() {
            System.out.println("O");
        }
    }
}

