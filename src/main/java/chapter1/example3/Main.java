package chapter1.example3;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class Main {
    public static void main(String[] args) {
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("B");
        MyThread c = new MyThread("C");

        a.start();
        b.start();
        c.start();
    }
}
