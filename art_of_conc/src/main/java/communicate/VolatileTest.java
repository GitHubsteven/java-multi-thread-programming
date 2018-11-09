package communicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 线程通过volatile来通信
 * @Date: Created at 16:16 2018/11/3.
 */
public class VolatileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    private static volatile int MY_INT = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;   //如果没有volatile的话，那么这里的local_value 就不会和全局变量MY_INT的数据保持一致了
            while (local_value < 5) {
                if (local_value != MY_INT) {
                    LOGGER.info("Got Change for MY_INT : {}", MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT < 5) {
                LOGGER.info("Incrementing MY_INT to {}", local_value + 1);
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}