package chapter8_con_framework;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 工作窃取算法
 * @Date: Created at 17:13 2018/11/23.
 */
public class WorkStealing {
    private static ArrayBlockingQueue<Runnable> taskQueue1 = new ArrayBlockingQueue<>(10);
    private static ArrayBlockingQueue<Runnable> taskQueue2 = new ArrayBlockingQueue<>(10);
    Map<Worker, ArrayBlockingQueue<Runnable>> worker2WorkMap = new ConcurrentHashMap<>();
    ArrayList<Worker> workers = new ArrayList<>();

    public static void main(String[] args) {

    }
}