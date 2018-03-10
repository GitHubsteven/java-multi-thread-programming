package chapter1.example2;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        for(int i=1, size =10; i <= size ;i ++){
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println(" run = "+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
