package com.neusoft.entityDAO;

import com.neusoft.entity.people.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientDAO {
    public boolean addPatient(Patient patient) throws IOException;

    public void updatePatient(Patient patient) throws IOException;

    public void deletePatient(Patient patient) throws IOException;

    public List<Patient> findAllPatient() throws IOException;

    public Patient findPatientByID(String id) throws IOException;

    public Patient findPatientByName(String name) throws IOException;

    public Patient findPatientByLoginName(String loginName) throws IOException;
}
