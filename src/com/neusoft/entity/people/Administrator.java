package com.neusoft.entity.people;

public class Administrator extends Person {


    public Administrator(String name, int age, String sex, String id, String loginName,
                         String password, String telephoneNumber) {
        super(name, age, sex, id, loginName, password, telephoneNumber);
    }

    public Administrator() {
    }

    public Administrator(String name, int age, String sex, String id, String loginName, String password, String telephoneNumber, boolean isSelect) {
        super(name, age, sex, id, loginName, password, telephoneNumber, isSelect);
    }
}
