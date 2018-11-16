package asa.com.beauty.of.conc.chapter2_other_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 使用 synchronized 关键宇的确可以实现线程安全性 ， 即内存可见性和原子性，但是
 * synchronized 是独占锁，没有获取内部锁的线程会被阻塞掉 ，而这里的 getCount 方法只是
 * 读操作，多个线程同时调用不会存在线程安全问题 。 但是加了关键宇 synchronized 后，同
 * 一时间就只能有一个线程可以调用，这显然大大降低了并发性 。 你也许会间，既然是只读
 * 操作，那为何不去掉 getCount 方法上的 synchronized 关键字呢？其实是不能去掉的，别忘
 * 了 这里要靠 synchronized 来实现 value 的 内 存可见性 。那么有没有更好的实现呢？答案是
 * 肯定 的，下面将讲到的在内部使用非阻塞 CAS 算法实现的原子性操作类 AtomicLong 就是
 * 一个不错的选择 。
 * CAS算法经典问题 ABA问题，你看到的不一定是正真的。避免这个问题就是避免回环，避免回环的最好
 * 标准为时间戳。
 * @Date: Created at 14:36 2018/11/16.
 */
public class ThreadSafeCount {
    private Long value;

    public synchronized Long getValue() {
        return value;
    }

    public synchronized void setValue(Long value) {
        this.value = value;
    }
}