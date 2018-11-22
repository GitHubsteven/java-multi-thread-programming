package atomic;

import util.SleepUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: atomic的lazySet是个很有意思的函数，最终会设置成newValue，使用lazySet设置值后，可能导致其他
 * 线程在之后的一小段时间内还是可以读到旧的值。关于该方法的更多信息可以参考并发编程
 * 网翻译的一篇文章《AtomicLong.lazySet是如何工作的？》，文章地址是“http://ifeve.com/howdoes-atomiclong-lazyset-work/”。
 * @Date: Created at 13:15 2018/11/22.
 */
public class LazySet {
    private static final AtomicInteger value = new AtomicInteger(0);

    private final static CountDownLatch countDown = new CountDownLatch(2);

    public static void main(String[] args) {
        value.lazySet(2);

        //定义一个新的
        Thread thread_assign = new Thread(() -> value.lazySet(3), "assign_value_3");
        countDown.countDown();
        thread_assign.start();
        countDown.countDown();
//        SleepUtils.sencond(1);    //(code1)
        System.out.println("value: " + value.get());    //如果注释了 code1的话，结果大概率是2，而不是3，但是如果主线程隔段时间在获取的话
        //结果是3。其实，并不知道这个有什么用。
    }
}