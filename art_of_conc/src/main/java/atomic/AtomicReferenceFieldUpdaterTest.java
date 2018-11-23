package atomic;

import model.User;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:32 2018/11/22.
 */
public class AtomicReferenceFieldUpdaterTest {
    private static final AtomicReferenceFieldUpdater<User, Integer>
            userAgeUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, Integer.class, "old");

    public static void main(String[] args) {
        User conan = new User("conan", 10);
        System.out.println(userAgeUpdater.getAndSet(conan, 13));
        System.out.println(userAgeUpdater.get(conan));
    }
}