package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 10:00 2018/11/23.
 */
@Setter
@Getter
@ToString
public class User implements Serializable {
    private String name;
    public volatile Integer old;

    public User(String name, Integer old) {
        this.name = name;
        this.old = old;
    }
}