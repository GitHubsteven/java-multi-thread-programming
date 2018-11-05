package basic;

import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: thread 基本知识
 * @Date: Created at 13:06 2018/11/3.
 */
public class ThreadTest {
    public static void main(String[] args) {
        List<Thread> runnables = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            MyThread thread = new MyThread("thread" + x);
            runnables.add(thread);
        }

        for (Thread runnable : runnables) {
            runnable.start();
        }
    }

    public static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            /*
             * 在synchronized的对象的两个操作必须是原子的，在这个读写操作中，线程必须对对象独占，否则会出现问题
             * 这是在这里显示的场景，但是在其他场景我们不能这么保证，这里我们也没有通过cas算法来保证数据在多线程
             * 的安全性，
             *
             * 在这里操作的过程中，ide提示我需要报对象设置为final，原来是为了保证多个对象共享的是一个对象。
             */
//            synchronized (ThreadService.person) {
            ThreadService.setName("person name:" + getName());
            try {
                TimeUnit.MILLISECONDS.sleep(65);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("person name should be :" + getName() + " but get is :" + ThreadService.getName());
//            }

        }
    }
}