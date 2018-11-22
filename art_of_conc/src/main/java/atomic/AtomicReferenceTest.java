package atomic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 14:21 2018/11/22.
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> userAtomicRef = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("asa", 15);
        userAtomicRef.set(user);
        System.out.println(userAtomicRef.get());

        User updateUser = new User("land", 20);
        userAtomicRef.compareAndSet(user, updateUser);
        System.out.println(userAtomicRef.get());
    }


    @Setter
    @Getter
    @Builder
    @ToString
    static class User {
        private String name;
        private int old;
    }
}