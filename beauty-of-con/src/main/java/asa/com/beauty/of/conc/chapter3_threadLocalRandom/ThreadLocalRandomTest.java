package asa.com.beauty.of.conc.chapter3_threadLocalRandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 线程安全的随机生成数，random类不安全的原因是种子在多线程中会出现问题，而解决方案是
 * 在获取中子的算法中，增加了线程为key的识别依据，本质和threadLocal思想一样
 * @Date: Created at 18:29 2018/11/16.
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            //保持种子和线程之间的一一对应性，生成的过程中，线程只读取自己线程的值
            System.out.println(random.nextInt(5));
        }
    }
}