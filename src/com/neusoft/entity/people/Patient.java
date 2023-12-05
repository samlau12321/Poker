package com.neusoft.entity.people;

public class Patient extends Person {
    private String emergencyContact;
    private String emergencyPhoneNumber;
    private Doctor doctor;
    private String doctorName;

    public Patient(String name, int age, String sex, String id, String loginName,
                   String password, String telephoneNumber, String emergencyContact, String emergencyPhoneNumber, Doctor doctor)
    {
        super(name, age, sex, id, loginName, password, telephoneNumber);
        this.emergencyContact = emergencyContact;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.doctor = doctor;
        this.doctorName = null;
//        this.setSelect(false);
    }

    public Patient(String name, int age, String sex, String id, String loginName, String password, String telephoneNumber, String emergencyContact, String emergencyPhoneNumber) {
        super(name, age, sex, id, loginName, password, telephoneNumber);
        this.emergencyContact = emergencyContact;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.doctor = null;
        this.doctorName = null;
//        this.setSelect(false);
    }

    public Patient(){

    }

    public Patient(String name, int age, String sex, String id, String loginName, String password, String telephoneNumber, boolean Select, String emergencyContact, String emergencyPhoneNumber) {
        super(name, age, sex, id, loginName, password, telephoneNumber, Select);
        this.emergencyContact = emergencyContact;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDoctorName() {
        return doctor.getName();
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "emergencyContact='" + emergencyContact + '\'' +
                ", emergencyPhoneNumber='" + emergencyPhoneNumber + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
