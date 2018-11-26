package chapter6_con_framework;

import java.util.HashMap;
import java.util.UUID;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 13:26 2018/11/23.
 */
public class DeadLoop4HashMap {
    static final HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        try {
            Thread ftf = new Thread(() -> {
                //为什么我总是不会死循环，可能我给虚拟机定义的内存太大了吧。
                for (int i = 0; i < 3000000; i++) {
                    new Thread(() -> {
                        try {
                            System.out.println(Thread.currentThread().getName());
                            map.put(UUID.randomUUID().toString(), "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, "ftf" + i)
                            .start();
                }
            }, "ftf");

            ftf.start();
            ftf.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}