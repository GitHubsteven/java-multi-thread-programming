package chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:25 2018/11/26.
 */
public class TestBlocker {
    public static void main(String[] args) {
        TestBlocker testBlocker = new TestBlocker();
        testBlocker.testPark();
    }

    public void testPark() {
        LockSupport.park(this);
    }
}