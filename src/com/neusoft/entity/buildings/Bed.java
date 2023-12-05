package com.neusoft.entity.buildings;

import com.neusoft.entity.people.Patient;

public class Bed {
    private int id;
    private Patient patient;
    boolean using;

    public Bed(int id, Patient patient, boolean using) {
        this.id = id;
        this.patient = patient;
        this.using = using;
    }

    public Bed(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isUsing() {
        return using;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", patient=" + patient +
                ", using=" + using +
                '}';
    }
}
