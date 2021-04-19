package chapter8_con_tool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/4/9 15:55
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/4/9 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class LC_1117_H2O_Runner {
    public static void main(String[] args) {
        LC_1117_H2O_Creator creator = new LC_1117_H2O_Creator();
        List<Thread> runnables = new ArrayList<>(99);
        for (int i = 0; i < 99; i++) {
            runnables.add(new Runner(creator, i));
        }

        for (Thread runnable : runnables) {
            runnable.start();
        }

    }

    public static class Runner extends Thread {
        private final LC_1117_H2O_Creator conductor;

        private final int value;

        public Runner(LC_1117_H2O_Creator conductor, int value) {
            this.conductor = conductor;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                if ((value % 3) == 0) {
                    conductor.hydrogen(() -> System.out.println("H"));
                } else {
                    conductor.oxygen(() -> System.out.println("O"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

