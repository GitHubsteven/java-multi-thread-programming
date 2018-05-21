package chapter1.example3;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class MyThread extends Thread {
    private int count = 5;
    MyThread(String name){
        super();
        setName(name);
    }

    @Override
    public void run() {
        while(count > 0){
            count --;
            System.out.println("count " + count + " in "+ Thread.currentThread().getName());
        }
    }
}
