package chapter6_con_framework;

import util.SleepUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 17:42 2018/11/23.
 */
public class SimpleWorkStealing {
    private static final int size = 10;
    private static Queue<Integer> first_task_queue = new LinkedList<>();
    private static Queue<Integer> second_task_queue = new LinkedList<>();
    private static Random random = new Random();

    static {
        for (int i = 0; i < size; i++) {
            int first = random.nextInt(10);  //more
            first_task_queue.offer(first);
            int second = random.nextInt(5);
            second_task_queue.offer(second);
        }
    }

    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            doBusiness(threadName, first_task_queue, second_task_queue);
        }, "first");

        Thread second = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            doBusiness(threadName, second_task_queue, first_task_queue);
        }, "second");

        first.start();
        second.start();
    }

    private static void doBusiness(String threadName, Queue<Integer> second_task_queue, Queue<Integer> first_task_queue) {
        while (true) {
            Integer current;
            if (second_task_queue.size() != 0 && (current = second_task_queue.poll()) != null) {
                System.out.println(String.format("thread[%s] start to sleep %d second", threadName, current));
                SleepUtils.sencond(current);
            } else if (first_task_queue.size() != 0 && (current = first_task_queue.poll()) != null) {
                System.out.println(String.format("thread[%s] ----steal--- %d second to sleep ", threadName, current));
                SleepUtils.sencond(current);
            } else {
                System.out.println("end the thread: " + threadName);
                return;
            }
        }
    }
}