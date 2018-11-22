package chapter9_threadpool;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 10:55 2018/11/22.
 */
public class GetProcessors {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("availableProcessors count: " + runtime.availableProcessors());
        System.out.println("free memory: " + runtime.freeMemory());
    }
}