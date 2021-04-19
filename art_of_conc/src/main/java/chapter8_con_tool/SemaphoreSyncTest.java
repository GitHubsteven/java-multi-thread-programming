package chapter8_con_tool;

import java.util.concurrent.Semaphore;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/3/1
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/3/1 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class SemaphoreSyncTest {

    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        System.out.println(semaphore.availablePermits());
        semaphore.release(2);
        System.out.println(semaphore.availablePermits());
    }
}
