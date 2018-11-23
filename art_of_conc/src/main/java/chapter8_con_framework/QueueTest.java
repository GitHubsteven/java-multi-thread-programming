package chapter8_con_framework;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:16 2018/11/23.
 */
public class QueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue fairQuue = new ArrayBlockingQueue(1000, true);
    }
}