package chapter5;

import java.sql.SQLOutput;
import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * 当一个线程调用 unpark 时，如果参数 thread 线程没有持有 thread 与 LockSupport 类
 * 关联的许可证， 则 让 thread 线程持有。 如果 thread 之前因调用 park（）而被挂起，则调用
 * unpark 后，该线程会被唤醒 。 如果 thread 之前没有调用 park ，则 调用 unpark 方法后 ， 再
 * 调用 park 方法，其会立刻返回
 * @Date: Created at 11:09 2018/11/26.
 */
public class ParkAndUnPark {
    public static void main(String[] args) {
        System.out.println("begin park");
        //使当前线程获取许可证
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park");
    }


}