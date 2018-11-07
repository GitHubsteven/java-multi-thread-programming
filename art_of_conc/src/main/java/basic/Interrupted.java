package basic;

import util.SleepUtils;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: interrupt 是指中断一个线程的行为，但是isInterrupted是指获取一个线程的中断标志位，如果线程sleep了，那么标志位会被清除，返回为false
 * @Date: Created at 11:39 2018/11/7.
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread sleepRunner = new Thread(new SleepRunner(), "Sleep Runner thread");
        Thread workRunner = new Thread(new WorkRunner(), "Work Runner thread");
        //启动线程
        sleepRunner.start();
        workRunner.start();

        SleepUtils.sencond(5);
        //中断线程
        sleepRunner.interrupt();
        workRunner.interrupt();
        System.out.println("sleepRunner interrupted is: " + sleepRunner.isInterrupted());
        System.out.println("workRunner interrupted is: " + workRunner.isInterrupted());
        //防止sleepRunner和work runner立刻退出
        SleepUtils.sencond(2);
    }


    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.sencond(10);
            }
        }
    }

    static class WorkRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                //working,working
            }
        }
    }
}