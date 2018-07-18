package jenkov;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/10
 * @Time: 11:12
 * @Description:
 * @version: 1.0.0
 */
public class CASTest {
    public static class MyLock {
        private AtomicBoolean locked = new AtomicBoolean(false);

        public boolean lock() {
            return locked.compareAndSet(false, true);
        }

    }
}