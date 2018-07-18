package chapter1.example1;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class Main {
    public static void main(String[] args) {
        MyThread t10 = new MyThread(10);
        MyThread t11 = new MyThread(11);
        MyThread t12 = new MyThread(12);
        MyThread t13 = new MyThread(13);
        MyThread t14 = new MyThread(14);
        MyThread t15 = new MyThread(15);
        MyThread t16 = new MyThread(16);
        MyThread t17 = new MyThread(17);
        MyThread t18 = new MyThread(18);
        MyThread t19 = new MyThread(19);
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        t17.start();
        t18.start();
        t19.start();
    }
}
