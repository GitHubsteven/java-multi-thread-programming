package pers.demo.idv.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/6/3 19:16
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/6/3 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class TokenCreator {
    public static AtomicInteger tokenLimiter = new AtomicInteger(0);

    public static void main(String[] args) {
        while (tokenLimiter.get() < 100) {
            System.out.printf(">>>>token number is: %d at %d\n", tokenLimiter.get(), System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tokenLimiter.getAndIncrement();
        }
    }
}

