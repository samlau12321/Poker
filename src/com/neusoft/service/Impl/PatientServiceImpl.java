package com.neusoft.service.Impl;

import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.service.PatientService;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {
    String filename = "Patients.json";

    private static PatientServiceImpl patientService = null;

    private PatientServiceImpl(){

    }

    public static PatientServiceImpl getPatientService(){
        if (patientService == null){
            patientService = new PatientServiceImpl();
        }

        return patientService;
    }

    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();

    @Override
    public void save(Object obj, String filename, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, filename, mode);
    }

    @Override
    public List<Object> queryAll(String filename) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Object.class);
        return list;
    }

    @Override
    public boolean validate(String loginName, String password, String filename) throws IOException {
        boolean login = false;
        List<Patient> patientList;
        patientList = patientDAO.findAllPatient();
        for (Patient patient : patientList){
            if (patient.getLoginName().equals(loginName) && patient.getPassword().equals(password)){
                login = true;
                break;
            }
        }
        return login;
    }

    @Override
    public void changePersonalInfo(Patient patient) throws IOException {
        List<Patient> list = new ArrayList<>();
        list = patientDAO.findAllPatient();
        for (Patient p : list){
            if (p.getId().equals(patient.getId())){
                int index = list.indexOf(p);
                list.set(index, patient);
                break;
            }
        }
    }

    @Override
    public boolean resignPatient(Patient patient) throws IOException {
        boolean isResignPatient = patientDAO.addPatient(patient);
        return isResignPatient;
    }
}
