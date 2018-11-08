package online;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:05 2018/11/8.
 */
public class TheDemo {
    public synchronized void test(String name) {
        for (int i = 0; i < 10; i++) {
            SOP.print(name + " :: " + i);
            try {
                Thread.sleep(500);     //The thread does not lose ownership of any monitors.
            } catch (Exception e) {
                SOP.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        TheDemo theDemo = new TheDemo();
        new TestThread("THREAD 1", theDemo);
        new TestThread("THREAD 2", theDemo);
        new TestThread("THREAD 3", theDemo);
    }
}