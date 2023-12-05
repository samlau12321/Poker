package com.neusoft.service;

import com.neusoft.entity.people.Patient;

import java.io.IOException;

public interface PatientService extends BaseService{
    public void changePersonalInfo(Patient patient) throws IOException;

    public boolean resignPatient(Patient patient) throws IOException;
}
