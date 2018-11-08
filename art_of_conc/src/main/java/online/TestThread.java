package online;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:04 2018/11/8.
 */
public class TestThread extends Thread {
    String name;
    TheDemo theDemo;
    public TestThread(String name,TheDemo theDemo) {
        this.theDemo = theDemo;
        this.name = name;
        start();
    }
    @Override
    public void run() {
        theDemo.test(name);
    }
}

