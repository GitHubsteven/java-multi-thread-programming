package chapter1.example1;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class MyThread extends Thread {
    private int id;
    MyThread(int id){
        this.id =  id;
    }

    @Override
    public void run() {
        System.out.println(id);
    }
}
