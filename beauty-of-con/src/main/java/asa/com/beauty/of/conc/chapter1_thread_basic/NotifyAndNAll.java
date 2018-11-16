package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 测试notify和notifyAll的
 * @Date: Created at 10:55 2018/11/16.
 */
public class NotifyAndNAll {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread waiter1 = new Thread(new Waiter(), "waiter1");
        Thread waiter2 = new Thread(new Waiter(), "waiter2");

        Thread notify = new Thread(new Notifier(), "notify");

        waiter1.start();
        waiter2.start();

        notify.start();
    }

    public static class Waiter implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (obj) {
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println("thread[" + currentThreadName + "]get lock and start to wait");
                    obj.wait();
                    System.out.println("thread[ " + currentThreadName + "] end the wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Notifier implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                String currentName = Thread.currentThread().getName();
                System.out.println("[" + currentName + "] get lock and start to notify the wait thread of the obj");
                /*
                thread[waiter1]get lock and start to wait
                thread[waiter2]get lock and start to wait
                [notify] get lock and start to notify the wait thread of the obj
                thread[ waiter1] end the wait
                 */
//                obj.notify();

                obj.notifyAll();
                /*
                thread[waiter2]get lock and start to wait
                thread[waiter1]get lock and start to wait
                [notify] get lock and start to notify the wait thread of the obj
                thread[ waiter1] end the wait
                thread[ waiter2] end the wait

                 */
            }
        }
    }
}