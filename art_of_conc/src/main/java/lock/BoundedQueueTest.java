package lock;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 9:45 2018/11/13.
 */
public class BoundedQueueTest {
    private static final BoundedQueue<Integer> queue = new BoundedQueue<>(5);

    public static void main(String[] args) {
        new Thread(new Adder(), "Thread_add").start();
        new Thread(new Remover(), "Thread_remove").start();
    }

    static class Adder implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    queue.add(i);
                }
                System.out.println("add 5 element to queue successfully!");
                System.out.println("add 6th element to queue start!");
                queue.add(6);
                System.out.println("add 6th element to queue successfully!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Remover implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("remove element from queue start!");
                System.out.println(queue.remove());
                System.out.println("remove element from queue successfully!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}