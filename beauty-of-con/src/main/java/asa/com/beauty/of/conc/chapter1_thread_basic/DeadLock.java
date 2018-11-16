package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 死锁的产生必须具备以下四个条件
 * 1. 互斥条件：资源的排他性
 * 2. 请求并持有： 持有资源者发送新的互斥资源请求
 * 3. 不可剥夺性： 一旦持有资源，除了自己没有线程可释放或者枪战资源
 * 4. 环路等待： 资源的等待形成环路。
 * @Date: Created at 13:22 2018/11/16.
 */
public class DeadLock {
    private final static Object resourceA = new Object();
    private final static Object resourceB = new Object();

    public static void main(String[] args) {

        Thread thread_1 = new Thread(() -> {
            String currentName = Thread.currentThread().getName();
            try {
                synchronized (resourceA) {
                    System.out.println(String.format("thread:[%s] get resourceA lock", currentName));
                    System.out.println(String.format("thread:[%s] sleep 3s  and try to get resourceB lock", currentName));
                    Thread.sleep(1000 * 3);

                    synchronized (resourceB) {
                        System.out.println(String.format("thread:[%s] get resourceB lock", currentName));
                        System.out.println(String.format("thread:[%s] end the business", currentName));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread_1");

        Thread thread_2 = new Thread(() -> {
            String currentName = Thread.currentThread().getName();
            try {
                synchronized (resourceB) {
                    System.out.println(String.format("thread:[%s] get resourceB lock", currentName));
                    System.out.println(String.format("thread:[%s] sleep 3s and try to get resourceA lock", currentName));
                    Thread.sleep(1000 * 3);

                    synchronized (resourceA) {
                        System.out.println(String.format("thread:[%s] get resourceA lock", currentName));
                        System.out.println(String.format("thread:[%s] end the business", currentName));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread_2");

        thread_1.start();
        thread_2.start();
    }

}