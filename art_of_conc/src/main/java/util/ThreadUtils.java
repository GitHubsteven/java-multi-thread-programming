package util;

/**
 * @author rongbin.xie
 * @version 1.0.0
 * @date 2021/4/9 13:52
 * @description
 * @copyright COPYRIGHT © 2014 - 2021/4/9 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 **/
public class ThreadUtils {
    public static void main(String[] args) {

    }

    public static int getSuggestThreadCount() {
        return Runtime.getRuntime().availableProcessors() * 2 + 2;
    }
}

