package chapter5;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 10:30 2018/11/15.
 */
public class Sync extends AbstractQueuedSynchronizer {
    //是否处于占用状态
    public boolean isHeldExclusively() {
        return getState() == 1;
    }

    //当状态为0的时候获取锁，这个acquires参数有啥用吗？
    public boolean tryAcquire(int acquires) {
        assert acquires == 1;
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    //释放锁，将状态设置为0
    public boolean tryRelease(int releases) {
        assert releases == 1;
        if (getState() == 0) throw new IllegalMonitorStateException(null);
        //释放线程
        setExclusiveOwnerThread(null);
        //设置状态
        setState(0);
        return true;
    }

    //返回一个condition，每个condition都含有一个condition队列
    public Condition newCondition() {
        return new ConditionObject();
    }

    public final void aqcuire(int arg) {
    }


}