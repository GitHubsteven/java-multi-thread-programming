package chapter8_con_tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/3/1
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/3/1 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class Lc1114PrintInOrder {

    public static void main(String[] args) throws InterruptedException {

        Foo foo = new Foo();
        foo.second(new PrintTask(2));
        foo.third(new PrintTask(3));
        foo.first(new PrintTask(1));
    }


    public static class PrintTask extends Thread {

        public static String[] messages = {"first", "second", "third"};

        private final int tip;

        public PrintTask(int tip) {
            this.tip = tip;
        }

        @Override
        public void run() {
            if (tip > messages.length) {
                throw new RuntimeException("tip is extensive!");
            }
            System.out.print(messages[tip - 1]);
        }
    }
}
