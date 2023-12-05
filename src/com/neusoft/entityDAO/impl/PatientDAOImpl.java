package com.neusoft.entityDAO.impl;

import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.PatientDAO;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private static PatientDAOImpl patientDAO = null;
    private PatientDAOImpl(){

    }

    public static PatientDAOImpl getPatientDAO(){
        if (patientDAO == null){
            patientDAO = new PatientDAOImpl();
        }

        return patientDAO;
    }
    String fileName = "Patients.json";

    @Override
    public boolean addPatient(Patient patient) throws IOException {
        boolean add = true;
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);
        if (list != null){
            for(int i = 0 ;i < list.size(); i++){
                Patient patient1 =(Patient) list.get(i);
                String id = patient1.getId();
                if (id.equals(patient.getId())){
                    add = false;
                    break;
                }
            }
        }

        String json_string = GsonUtil.toJson(patient);
        if (json_string.length() > 0 && add){
            FileUtil.writeData(fileName, json_string, true);
        }

        return add;
    }

    @Override
    public void updatePatient(Patient patient) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);//传入json数据代表的那个list
        List<Patient> patientArrayList = new ArrayList<>();
        if (list != null) {
            for (Object o : list) {
                patientArrayList.add((Patient) o);
            }
        }

        for (Patient administrator1 : patientArrayList) {
            if (administrator1.getId().equals(patient.getId())) {
                patientArrayList.set(patientArrayList.indexOf(administrator1), patient);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Patient patient1 : patientArrayList){
            if (patientArrayList.indexOf(patient1) == patientArrayList.size() - 1){
                data.append(GsonUtil.toJson(patient1));
            }
            else{
                data.append(GsonUtil.toJson(patient1)).append("\n");
            }
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public void deletePatient(Patient patient) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);//传入json数据代表的那个list
        List<Patient> administratorList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                administratorList.add((Patient) o);
            }
        }

        for (Patient administrator1 : administratorList){
            if (administrator1.getId().equals(patient.getId())){
                administratorList.remove(administrator1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Patient administrator_temp : administratorList){
            if (administratorList.indexOf(administrator_temp) == administratorList.size() - 1){
                data.append(GsonUtil.toJson(administrator_temp));
            }
            else
                data.append(GsonUtil.toJson(administrator_temp)).append("\n");
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public List<Patient> findAllPatient() throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);
        List<Patient> list1 = new ArrayList<>();
        for (Object o : list){
            list1.add((Patient) o);
        }
        return list1;//返回一个列表，里面有所有的Administrator对象
    }

    @Override
    public Patient findPatientByID(String id) throws IOException {
        Patient administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);
        List<Patient> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Patient) o);
        }

        for (Patient administrator : list1){
            if (administrator.getId().equals(id)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Patient对象
    }

    public Patient findPatientByName(String name) throws IOException {
        Patient administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);
        List<Patient> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Patient) o);
        }

        for (Patient administrator : list1){
            if (administrator.getName().equals(name)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Patient对象
    }

    @Override
    public Patient findPatientByLoginName(String loginName) throws IOException {
        Patient administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Patient.class);
        List<Patient> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Patient) o);
        }

        for (Patient administrator : list1){
            if (administrator.getLoginName().equals(loginName)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Patient对象
    }
}
