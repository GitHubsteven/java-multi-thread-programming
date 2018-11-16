package asa.com.beauty.of.conc.chapter2_other_basic;

import sun.misc.Contended;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 16:16 2018/11/16.
 */
public class FillLongField {
    /**
     * The current seed for a ThreadLocal Random
     */
    @Contended("tlr")
    long threadLocalRandomSeed;
    /**
     * Probe hash value ; nonzero 工f threadLocalRandomSeed in工 tialized
     */
    @Contended("tlr")
    int threadLocalRandomProbe;
    /**
     * Secondary seed isolated from public ThreadLocalRandomTest sequence
     */
    @Contended("tlr")
    int threadLocalRandomSecondarySeed;
}