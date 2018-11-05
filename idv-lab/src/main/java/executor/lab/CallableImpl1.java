package executor.lab;

import model.Person;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 17:10 2018/11/2.
 */
public class CallableImpl1 implements Callable<Person> {

    @Override
    public Person call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        Person person = new Person();
        person.setName("hello, schedule!");
        System.out.println(person.getName() + Math.random());
        return person;
    }
}