package com.neusoft.entity.people;

public class Doctor extends Person {
    private String bornDate;
    private String expertise;

    public Doctor(String name, int age, String sex, String id, String loginName,
                  String password, String telephoneNumber, String bornDate, String expertise)
    {
        super(name, age, sex, id, loginName, password, telephoneNumber);
        this.bornDate = bornDate;
        this.expertise = expertise;
    }

    public Doctor(){
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "bornDate='" + bornDate + '\'' +
                ", expertise='" + expertise + '\'' +
                '}';
    }
}
