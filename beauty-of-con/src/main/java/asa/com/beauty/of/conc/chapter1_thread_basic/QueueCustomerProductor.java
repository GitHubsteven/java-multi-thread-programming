package asa.com.beauty.of.conc.chapter1_thread_basic;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 10:06 2018/11/16.
 */
public class QueueCustomerProductor {
    // TODO: 2018/11/16 实现并发队例的插入和删除等操作
    private static final Queue<String> queue = new ArrayDeque<>(10);

} 