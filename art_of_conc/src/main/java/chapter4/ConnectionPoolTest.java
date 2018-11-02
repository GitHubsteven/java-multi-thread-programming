package chapter4;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/16
 * @Time: 10:55
 * @Description:
 * @version: 1.0.0
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 200;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("noGot connection: " + notGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException ignored) {
            }

            try {
                Connection connection = pool.fetchConnection(1000);
                if (connection != null) {
                    try {
                        connection.createStatement();
                        connection.commit();
                    } catch (SQLException ignored) {
                    } finally {
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    }
                } else {
                    noGot.incrementAndGet();
                }
            } catch (InterruptedException ignored) {
            } finally {
                count--;
            }
            end.countDown();
        }

    }
}