package chapter8_con_framework;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 17:16 2018/11/23.
 */
public class Worker extends Thread {
    private Thread thread;

    public Worker(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}