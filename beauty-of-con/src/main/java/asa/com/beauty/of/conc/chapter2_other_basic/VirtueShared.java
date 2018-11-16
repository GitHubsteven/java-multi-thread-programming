package asa.com.beauty.of.conc.chapter2_other_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: 伪共享
 * @Date: Created at 16:01 2018/11/16.
 */
public class VirtueShared {
    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        shared();
        virtueShared();
    }

    private static void shared() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long start = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 2 + j;
            }
        }
        System.out.println("cache time :" + (System.currentTimeMillis() - start));
    }

    private static void virtueShared() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long start = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[j][i] = i * 2 + j;
            }
        }
        System.out.println("cache time :" + (System.currentTimeMillis() - start));
    }
}