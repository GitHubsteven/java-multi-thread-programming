package spin;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 名字叫自旋，其实也没有啥。
 * @Date: Created at 13:35 2018/11/22.
 */
public class SpinTest {
    private static final int QUEUE_SIZE = 10;
    //基础数据
    private static final ArrayBlockingQueue<String> data = new ArrayBlockingQueue<>(QUEUE_SIZE);

    //初始化数据
    static {
        for (int i = 0; i < QUEUE_SIZE; i++) {
            data.offer("Str" + i);
        }
    }

    public static String getLock() {
        for (; ; ) {
            String current = data.poll();
            assert current != null;
            if (doGetLock(current)) return current;
        }
    }

    private static boolean doGetLock(String threadName) {
        return threadName.contains("5");
    }

    public static void main(String[] args) {
        System.out.println(getLock());
    }
}