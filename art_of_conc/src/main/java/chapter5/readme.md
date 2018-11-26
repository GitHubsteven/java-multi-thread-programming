**java并发编程的艺术第五章，Lock和同步队列。**

注意点：
1. setExclusiveOwnerThread(Thread.currentThread()); 万万没想到设置占用线程这么直接粗暴。
2. condition里面竟然是个队列。

**LockSupport:**
park
在其他线程调用 unpark(Thread thread）方法并且将当前线程作为参数时 ，调用 park 方
法而被阻塞的线程会返回。另外，如果其他线程调用了阻塞线程的 interrupt（）方法 ，设置
了中断标志或者线程被虚假唤醒，则阻塞线程也会返回。所以在调用 park 方法时最好也
使用循环条件判断方式。

需要注意的是，因调用 park（） 方法而被阻塞的线程被其他线程中断而返回 时并不会抛
出 InterruptedException 异常。

当一个线程调用 unpark 时，如果参数 thread 线程没有持有 thread 与 LockSupport 类
关联的许可证， 则 让 thread 线程持有。 如果 thread 之前因调用 park（）而被挂起，则调用
unpark 后，该线程会被唤醒 。 如果 thread 之前没有调用 park ，则 调用 unpark 方法后 ， 再
调用 park 方法，其会立刻返回 