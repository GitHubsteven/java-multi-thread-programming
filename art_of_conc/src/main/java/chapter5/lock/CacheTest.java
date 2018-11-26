package chapter5.lock;

import util.SleepUtils;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 16:22 2018/11/26.
 */
public class CacheTest {
    public static void main(String[] args) {
        Runner runner = new Runner("sub");
        runner.start();
        SleepUtils.sencond(1);
        Cache.put("common", "main");
        System.out.println(Cache.get("common"));
    }

    static class Runner extends Thread {
        public Runner(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            Cache.put("common", threadName);
        }
    }
}