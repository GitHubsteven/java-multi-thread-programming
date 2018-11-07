package basic;

import util.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 17:54 2018/11/7.
 */
public class Deprecated {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "Print thread");
        printThread.setDaemon(true);
        printThread.start();
        SleepUtils.sencond(3);
        printThread.suspend();
        System.out.println("print thread suspend at: " + dateFormat.format(new Date()));
        SleepUtils.sencond(3);
        printThread.resume();
        System.out.println("print thread resume at: " + dateFormat.format(new Date()));
        SleepUtils.sencond(3);
        //终止线程,
        printThread.stop();
        System.out.println("print thread stop at: " + dateFormat.format(new Date()));
        SleepUtils.sencond(3);
    }


    static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + " runs at: " + dateFormat.format(new Date()));
                SleepUtils.sencond(1);
            }
        }
    }
}