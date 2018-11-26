package chapter5;

import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 10:47 2018/11/26.
 */
public class ParkTest {
    public static void main(String[] args) {
        System.out.println("begin park!");
        LockSupport.park();           //线程被挂起，因为没有许可证
        System.out.println("end park!");
    }
}