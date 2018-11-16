package asa.com.beauty.of.conc.chapter1_thread_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 线程wait后只会释放调用wait的公共资源，而其他的资源是不会被释放的。
 * @Date: Created at 10:35 2018/11/16.
 */
public class AwakenLimited {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try to get resourceB lock");
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}