package asa.com.beauty.of.conc.chapter2_other_basic;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: ++value:
 * 这一步不是原子的，是否原子性看汇编代码
 * public void inc() ;
 * Code :
 * 0
 * 1 : dup
 * 2 : getfield #2   // value:J
 * 5: lconst 1
 * 6 : ladd
 * 7 : putfield #2  // value : J
 * 10 : return
 *
 * 那么如何保持原子性呢？加入synchronize
 * @Date: Created at 14:33 2018/11/16.
 */
public class ThreadNotSafeCount {
    private Long value;

    public Long getvalue() {
        return value;
    }

    public void inc() {
        ++value;


    }
}