package com.neusoft.service.Impl;

import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.DoctorDAOImpl;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.service.DoctorService;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private static DoctorServiceImpl doctorService = null;

    private DoctorServiceImpl(){

    }

    public static DoctorServiceImpl getDoctorService(){
        if (doctorService == null){
            doctorService = new DoctorServiceImpl();
        }

        return doctorService;
    }

    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();
    DoctorDAOImpl doctorDAO = DoctorDAOImpl.getDoctorDAO();

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
        List<Doctor> doctorList;
        doctorList = doctorDAO.findAllDoctor();
        for (Doctor doctor : doctorList){
            if (doctor.getLoginName().equals(loginName) && doctor.getPassword().equals(password)){
                login = true;
                break;
            }
        }

        return login;
    }

    @Override
    public List<Patient> showAllPatient() throws IOException {
        List<Patient> patientList = patientDAO.findAllPatient();
        return patientList;
    }

    @Override
    public Patient findPeopleByName(String name) throws IOException {
        Patient foundPatient = null;
        List<Patient> patientList = showAllPatient();
        for (Patient patient : patientList){
            if (patient.getName().equals(name)){
                foundPatient = patient;
                break;
            }
        }
        return foundPatient;
    }

    @Override
    public EvaluateResult evaluatePatient(Patient patient) {
        return null;
    }

    public boolean resignDoctor(Doctor doctor) throws IOException {
        boolean resign;
        resign = doctorDAO.addDoctor(doctor);
        return resign;
    }
}
