package basic;

import util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 19:41 2018/11/7.
 */
public class WaitNotify {
    static boolean flag = true;      //运行标志位
    private static final Object lock = new Object(); //被锁定对象

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "wait_thread");
        waitThread.start();  //start and get lock, but enter the waiting seq

        Thread notifyThread = new Thread(new Notify(), "notify_thread");
        notifyThread.start();       //secondly  start but can get the lock immediately.

    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                //获取锁后，直接等待
                while (flag) {
                    System.out.println("flag is true, wait thread waiting at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();    //当前线程进入waiting 状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("flag is false, wait thread runs at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println("flag is true, notify thread runs at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.notifyAll();
                    flag = false;
                    SleepUtils.sencond(5); //休息5s，释放锁，让其他线程开始使用锁，其他线程业可能没有获取到锁
                }
                //再次获取锁,前提是别的线程已经释放了锁
                synchronized (lock) {
                    System.out.println("flag is false, notify thread  hold lock again and runs at " +
                            new SimpleDateFormat("HH:mm:ss").format(new Date()) + " sleep 5 s");
                    SleepUtils.sencond(5);
                }
            }
        }
    }
}