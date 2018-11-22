关于atomic操作，我们不能绕开的是unsafe类，在atomic类中，实现数据同步的方式是：volatile + cas算法。

同时，在这个模式下的cas算法的实现基本依赖Unsafe类的实现，具体的不细说，另外，在GetAndIncrement的方法

中，为了一定能实现这个原子性，我们要重新谈谈自旋算法，也许这不叫算法，但暂时就这么叫吧。

自旋算法：
```
    public final int getAndIncrement() {
    //不停的循环
        for (;;) {
            int current = get();
            int next = current + 1;
            //直到内部达到某种条件，自动返回
            if (compareAndSet(current, next))
                return current;
        }
    }
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
```