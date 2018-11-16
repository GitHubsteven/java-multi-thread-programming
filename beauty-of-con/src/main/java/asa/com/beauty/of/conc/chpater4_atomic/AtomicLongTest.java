package asa.com.beauty.of.conc.chpater4_atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 18:40 2018/11/16.
 */
public class AtomicLongTest {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(2);
        atomicLong.incrementAndGet();
        atomicLong.compareAndSet(0, 1);
    }
}