package chapter8_con_tool;

import java.util.concurrent.Semaphore;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/3/1
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/3/1 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class AnotherDummy {
    /**
     * The count of permits is 0, the threads will be blocked.
     * Until, the resource can be acquired.
     */
//    private final Semaphore first = new Semaphore(0);
    private final Semaphore second = new Semaphore(0);
    private final Semaphore third = new Semaphore(0);

    public void one(Runnable runnable) throws InterruptedException {
        runnable.run();
//        first.release();
        second.release();
    }

    public void two(Runnable runnable) throws InterruptedException {
        second.acquire();
        runnable.run();
        second.release();
        third.release();
    }

    public void three(Runnable runnable) throws InterruptedException {
        third.acquire();
        runnable.run();
        third.release();
    }

}
