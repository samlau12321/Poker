package com.neusoft.entity.people;

public abstract class Person {
    private String name;
    private int age;
    private String sex;
    private String id;
    private boolean Select;
    private String loginName;
    private String password;
    private String telephoneNumber;


    public Person(String name, int age, String sex, String id, String loginName, String password, String telephoneNumber, boolean Select) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.Select = Select;
    }

    public Person(String name, int age, String sex, String id, String loginName, String password, String telephoneNumber) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean getSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        this.Select = select;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                ", Select=" + Select +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
