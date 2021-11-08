package pers.demo.idv.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/6/3 15:13
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/6/3 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class ThreadPoolAdder {
    public static void main(String[] args) {
        int i = 0;
        while (i < 100000) {
            ThreadPoolStatisticsListener.es.submit(new AdderTask("" + i));
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public static class AdderTask implements Runnable {
        private final String name;

        public AdderTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.printf("Task:%s from ThreadPoolAdder=====%n", name);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

