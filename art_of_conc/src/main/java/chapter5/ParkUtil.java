package chapter5;

import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 11:34 2018/11/26.
 */
public class ParkUtil {
    public static void main(String[] args) {
        ParkUtil parkUtil = new ParkUtil();
        parkUtil.parkUtil();
    }

    public void parkUtil() {
        LockSupport.parkUntil(this, 10);
    }
}