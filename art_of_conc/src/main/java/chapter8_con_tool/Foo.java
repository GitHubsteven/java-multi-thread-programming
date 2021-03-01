package chapter8_con_tool;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/3/1
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/3/1 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/

import java.util.concurrent.Semaphore;

public class Foo {
    private Semaphore seam_first_two = new Semaphore(0);

    private Semaphore seam_two_second = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        seam_first_two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam_first_two.acquire();
        printSecond.run();
        seam_two_second.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        seam_two_second.acquire();
        printThird.run();
    }
}
