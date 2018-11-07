package basic;

import util.SleepUtils;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:02 2018/11/7.
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        //use two blocked threads，one get lock successfully, and other failed
        new Thread(new Blocked(), "blocked-thread-1").start();
        new Thread(new Blocked(), "blocked-thread-2").start();
    }

    //不断的睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.sencond(100);
            }

        }
    }

    //在waiting.class 实例上等待
    static  class Waiting implements Runnable{
        @Override
        public void run() {
            while(true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //在Blocked.class的实例加上锁，不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while(true){
                    SleepUtils.sencond(100);
                }
            }
        }
    }
}