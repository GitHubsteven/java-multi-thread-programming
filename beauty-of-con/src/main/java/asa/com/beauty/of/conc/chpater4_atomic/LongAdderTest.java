package asa.com.beauty.of.conc.chpater4_atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 19:53 2018/11/16.
 */
public class LongAdderTest {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1L);
        longAdder.sum();
        long result = longAdder.sumThenReset();
        long sum = longAdder.sum();
        System.out.println("result: " + result + " sum after reset: " + sum);
    }
}