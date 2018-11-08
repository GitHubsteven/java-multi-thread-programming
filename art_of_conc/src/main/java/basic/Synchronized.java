package basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:15 2018/11/8.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){
        }
        //static synchronized method, add lock on synchronized class
        m();
    }

    public static synchronized  void m(){}
}