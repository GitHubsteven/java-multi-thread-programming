package jenkov;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/10
 * @Time: 11:02
 * @Description:
 * @version: 1.0.0
 */
public class BlockingQueue<T> {
    private List<T> queue = new LinkedList<>();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }


    public synchronized void enqueue(T item)
            throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Object dequeue()
            throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        return this.queue.remove(0);
    }
}