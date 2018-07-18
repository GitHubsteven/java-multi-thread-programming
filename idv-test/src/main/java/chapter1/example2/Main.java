package chapter1.example2;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class Main {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.setName("my thread");
        thread.start();
        for(int i = 1 ; i <=10; i++){
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
