package atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:34 2018/11/7.
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        int init = 11;
        List<String> details = getDetail(init);

        AtomicInteger count = new AtomicInteger(details.size());

        while (count.get() > 0) {
            details.forEach(it -> {
                System.out.println(it);
                count.decrementAndGet();
            });
            init -= 5;
            details = getDetail(init);
            count.set(details.size());
        }

    }

    public static List<String> getDetail(int i) {
        List<String> details = new ArrayList<>();
        for (int x = 0; x < i - 5; x++) {
            details.add("String" + x);
        }
        return details;
    }
}