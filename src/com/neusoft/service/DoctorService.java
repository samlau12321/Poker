package com.neusoft.service;

import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entity.people.Patient;

import java.io.IOException;
import java.util.List;

public interface DoctorService extends BaseService{
    public List<Patient> showAllPatient() throws IOException;

    public Patient findPeopleByName(String name) throws IOException;

    public EvaluateResult evaluatePatient(Patient patient);

    public boolean resignDoctor(Doctor doctor) throws IOException;

}
