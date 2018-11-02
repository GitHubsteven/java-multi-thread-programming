package chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author rongbin.xie
 * @Description:
 * @Date: Created at 17:45 2018/5/21.
 */
public class ThreadInfo {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        java.lang.management.ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (java.lang.management.ThreadInfo threadInfo : threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+":"+threadInfo.getThreadName()+"]");
        }
    }
}

