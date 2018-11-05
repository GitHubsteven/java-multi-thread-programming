package basic;

import model.Person;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 13:46 2018/11/3.
 */
public class ThreadService {
    public static final Person person = new Person();

    public static void setName(String name) {
        person.setName(name);
    }

    public static String getName() {
        return person.getName();
    }
}