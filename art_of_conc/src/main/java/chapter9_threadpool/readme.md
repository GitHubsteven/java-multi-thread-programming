**什么是线程池**

线程池是运用场景最多的并发框架，用来管理线程。

**为什么要用线程池**

1. 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
2. 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
3. 提高线程的可管理性。线程是稀缺资源，如果无限制地创建，不仅会消耗系统资源，
还会降低系统的稳定性，使用线程池可以进行统一分配、调优和监控。但是，要做到合理利用
线程池，必须对其实现原理了如指掌。

**创建线程池**

创建一个线程池时需要输入几个参数，如下。
1. corePoolSize（线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线
程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任
务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads()方法，
线程池会提前创建并启动所有基本线程。
2. runnableTaskQueue（任务队列）：用于保存等待执行的任务的阻塞队列。可以选择以下几
个阻塞队列。
- ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原
则对元素进行排序。
- LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通
常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
- SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用
移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于Linked-BlockingQueue，静态工
厂方法Executors.newCachedThreadPool使用了这个队列。
- PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
3. maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并
且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是，如
果使用了无界的任务队列这个参数就没什么效果。
4. ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设
置更有意义的名字。使用开源框架guava提供的ThreadFactoryBuilder可以快速给线程池里的线
程设置有意义的名字，代码如下。
    ```
    new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
    ```
5. RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状
态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法
处理新任务时抛出异常。在JDK 1.5中Java线程池框架提供了以下4种策略。
- AbortPolicy：直接抛出异常。
- CallerRunsPolicy：只用调用者所在线程来运行任务。
- DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
- DiscardPolicy：不处理，丢弃掉。
当然，也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录
日志或持久化存储不能处理的任务。
- keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以，
如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率。
- TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS）、小时（HOURS）、分钟
（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）。

**线程池原理**
```
start: submit runnable
    if coreThreadPool isNot full
        create and run
    else 
        if taskQueue isnot full
            store runnable into taskQueue
        else if threadpoll isnot pull  
               create thread and run
             else 
                handle according to handler strategy 
``` 

关于threadPool内部关系的描述：
```
1. coreThreadPool E) threadpool(maximumpoll)
2. taskqueue/blockingQueue
3. abort strategy
```


**合理地配置线程池**
要想合理地配置线程池，就必须首先分析任务特性，可以从以下几个角度来分析。
- 任务的性质：CPU密集型任务、IO密集型任务和混合型任务。
- 任务的优先级：高、中和低。
- 任务的执行时间：长、中和短。
- 任务的依赖性：是否依赖其他系统资源，如数据库连接。

性质不同的任务可以用不同规模的线程池分开处理。CPU密集型任务应配置尽可能小的
线程，如配置Ncpu+1个线程的线程池。由于IO密集型任务线程并不是一直在执行任务，则应配
置尽可能多的线程，如2*Ncpu。混合型的任务，如果可以拆分，将其拆分成一个CPU密集型任务
和一个IO密集型任务，只要这两个任务执行的时间相差不是太大，那么分解后执行的吞吐量
将高于串行执行的吞吐量。如果这两个任务执行时间相差太大，则没必要进行分解。可以通过
Runtime.getRuntime().availableProcessors()方法获得当前设备的CPU个数。
优先级不同的任务可以使用优先级队列PriorityBlockingQueue来处理。它可以让优先级高
的任务先执行。

执行时间不同的任务可以交给不同规模的线程池来处理，或者可以使用优先级队列，让
执行时间短的任务先执行。
依赖数据库连接池的任务，因为线程提交SQL后需要等待数据库返回结果，等待的时间越
长，则CPU空闲时间就越长，那么线程数应该设置得越大，这样才能更好地利用CPU。

建议使用有界队列。有界队列能增加系统的稳定性和预警能力，可以根据需要设大一点
儿，比如几千。有一次，我们系统里后台任务线程池的队列和线程池全满了，不断抛出抛弃任
务的异常，通过排查发现是数据库出现了问题，导致执行SQL变得非常缓慢，因为后台任务线
程池里的任务全是需要向数据库查询和插入数据的，所以导致线程池里的工作线程全部阻
塞，任务积压在线程池里。如果当时我们设置成无界队列，那么线程池的队列就会越来越多，
有可能会撑满内存，导致整个系统不可用，而不只是后台任务出现问题。当然，我们的系统所
有的任务是用单独的服务器部署的，我们使用不同规模的线程池完成不同类型的任务，但是
出现这样问题时也会影响到其他任务