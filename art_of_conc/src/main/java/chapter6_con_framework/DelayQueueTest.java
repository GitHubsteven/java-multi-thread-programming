package chapter6_con_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:25 2018/11/23.
 */
public class DelayQueueTest {
    private static final AtomicLong sequencer = new AtomicLong(0);

    private static final ExecutorService executor = Executors.newScheduledThreadPool(1);

    //    ScheduledFutureTask<Runnable r, V result, long ns, long period>{
//
//    }
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    }
}