package communicate;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 8:20 2018/11/13.
 */
public class WaitTimed {


    static class WaitListener implements Runnable {
        @Override
        public void run() {
        }

        public synchronized Object get(long remain) throws InterruptedException {
            Object result = null;
            long end = System.currentTimeMillis() + remain;
            while ((result == null) && remain > 0) {
                wait(remain);
                remain = end - System.currentTimeMillis();
            }
            return result;
        }
    }

}