package chapter6_con_framework;

import java.util.Random;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 18:47 2018/11/23.
 */
public class Test {
    public static void main(String[] args) {
        while (true) {
            Random random = new Random();
            int value = random.nextInt(10);
            if (value % 2 == 0) {
                System.out.println("value is: " + value);
                return;
            }
        }
    }
}