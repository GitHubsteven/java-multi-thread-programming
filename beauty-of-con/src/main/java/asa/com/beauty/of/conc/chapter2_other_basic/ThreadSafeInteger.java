package asa.com.beauty.of.conc.chapter2_other_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 对于同步来说，一般setter/getter都是要有synchronzed的，读依然要，这是为了
 * 保证数据的一致性
 * @Date: Created at 14:17 2018/11/16.
 */
public class ThreadSafeInteger {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}