**线程之间的通信**

1. volatile和上synchronized关键字
2. wait/notify 通知机制
3. 管道输入/输出流
4. 线程之间的插入，join/countLatchDown/cycleBarrier/semaphore
5. threadLocal的使用

对于基于synchronized+ wait/notify而言，一个线程获取到锁有两个选择
1. 执行业务，这个时候占用锁
2. 执行条件不满足，放弃锁，进入waiting 状态，这个时候，锁会被其他线程获取，直到这个线程被通知，并且被分配到锁，
重新执行开始。

threadLocal的实现原理很简单，静态的线程安全的map，以线程对象为key，这样的话，就可以根据线程来读取值了。