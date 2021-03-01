package chapter8_con_tool;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/3/1
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/3/1 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        AnotherDummy ad = new AnotherDummy();

        Thread t1 = new Thread(() -> {
            try {
                ad.one(() -> {
                    System.out.println("One");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                ad.two(() -> {
                    System.out.println("Two");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                ad.three(() -> System.out.println("Three"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t2.start();
        t1.start();

    }
}
