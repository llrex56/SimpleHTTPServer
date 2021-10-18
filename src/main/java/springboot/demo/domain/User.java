package springboot.demo.domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String id;

    private String name;

    private int age;

    public User() {}

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
}
