**Java并发包中原子操作类原理剖析**
JUC 并发包中包含有 Atomiclnteger 、 AtomicLong 和 AtomicBoolean 等原子性操作类 ，
它们的原理类似，本章讲解 AtomicLong 类。 AtomicLong 是原子性递增或者递减类，其内
部使用 Unsafe 来实现，我们看下面的代码。