package asa.com.beauty.of.conc.chapter2_other_basic;

//import sun.misc.Contended;


import asa.com.beauty.of.conc.chapter2_other_basic.annotation.Contended;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 16:15 2018/11/16.
 */
@Contended
public class FilledLong {
    public volatile long value = 0L;
}