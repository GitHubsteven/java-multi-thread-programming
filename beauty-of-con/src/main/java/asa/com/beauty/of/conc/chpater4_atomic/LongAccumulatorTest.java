package asa.com.beauty.of.conc.chpater4_atomic;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((left, right) -> left + right, 0);
        long result = accumulator.get();
        System.out.println(result);
    }
}
