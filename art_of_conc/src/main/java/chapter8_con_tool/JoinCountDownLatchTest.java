package chapter8_con_tool;

import util.SleepUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完
 * 成，这里就传入N。
 * 当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法
 * 会阻塞当前线程，直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个
 * 点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，只需要把这个
 * CountDownLatch的引用传递到线程里即可。
 * 如果有某个解析sheet的线程处理得比较慢，我们不可能让主线程一直等待，所以可以使
 * 用另外一个带指定时间的await方法——await（long time，TimeUnit unit），这个方法等待特定时
 * 间后，就会不再阻塞当前线程。join也有类似的方法
 * @Date: Created at 10:31 2018/11/23.
 */
public class JoinCountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("1");
//            countDownLatch.countDown();
            System.out.println("2");
            SleepUtils.sencond(6);
            countDownLatch.countDown();
        }, "parser").start();
        countDownLatch.await(5, TimeUnit.SECONDS);
        System.out.println("3");
    }
}