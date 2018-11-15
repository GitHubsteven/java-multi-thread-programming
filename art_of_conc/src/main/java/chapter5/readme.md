**java并发编程的艺术第五章，Lock和同步队列。**

注意点：
1. setExclusiveOwnerThread(Thread.currentThread()); 万万没想到设置占用线程这么直接粗暴。
2. condition里面竟然是个队列。