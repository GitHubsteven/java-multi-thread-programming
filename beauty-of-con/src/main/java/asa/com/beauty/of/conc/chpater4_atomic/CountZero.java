package asa.com.beauty.of.conc.chpater4_atomic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 这里的代码有问题
 * @Date: Created at 19:08 2018/11/16.
 */
public class CountZero {
    private static volatile Integer count = 0;

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread thread_1 = new Thread(() -> {
            synchronized (count) {
                for (int i = 0; i < arrayOne.length; i++) {
                    // TODO: 2018/11/16 这代码有问题，但是现在不知道怎么解决，没有原子性，那么怎么才能呈现问题呢？
                    if (arrayOne[i] == 0) count++;
                }
            }

        }, "thread_1");

        Thread thread_2 = new Thread(() -> {
            synchronized (count) {
                for (int i = 0; i < arrayTwo.length; i++) {
                    if (arrayOne[i] == 0) count++;
                }
            }
        }, "thread_2");
        thread_1.start();
        thread_2.start();
        thread_1.join();
        thread_2.join();
        System.out.println("count of zero: " + count);

    }
}