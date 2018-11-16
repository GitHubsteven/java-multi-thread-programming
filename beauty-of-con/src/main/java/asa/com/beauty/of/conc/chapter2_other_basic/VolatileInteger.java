package asa.com.beauty.of.conc.chapter2_other_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: volatile 虽然提供了可见性保证，但并不保证操作的原子性 。
 * 理解：可以保证读操作在上一次的写操作之后，但是无法保证读写操作之间的封闭性吗？
 *
 * 那么什么时候才使用volatile关键字呢？
 * 1. 写入变量不依赖变量的当前值（你是指CAS的算法吗？）如果依赖当前值，流程将是 获取
 * ->计算->写入三部操作，而这三步不是原子的，volatile不保证原子性
 *
 * 2. 读写变量没有加锁，因为这已经保持了内存的可读性。
 *
 * 问题：什么是原子性操作？系列操作要么全不执行，要么全不执行
 * @Date: Created at 14:19 2018/11/16.
 */
public class VolatileInteger {
    private volatile int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}