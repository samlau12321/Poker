package com.neusoft.entityDAO.impl;

import com.neusoft.entity.people.Administrator;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entityDAO.DoctorDAO;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    //以下代码用来实现单例模式
    private static DoctorDAOImpl doctorDAO = null;
    private DoctorDAOImpl(){
    }
    public static DoctorDAOImpl getDoctorDAO(){
        if (doctorDAO == null){
            doctorDAO = new DoctorDAOImpl();
        }

        return doctorDAO;
    }
    String fileName = "Doctors.json";

    @Override
    public boolean addDoctor(Doctor doctor) throws IOException {
        boolean add = true;
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);
        if (list != null){
            for(int i = 0 ;i < list.size(); i++){
                Doctor doctor1 =(Doctor) list.get(i);
                String id = doctor1.getId();
                if (id.equals(doctor.getId())){
                    add = false;
                    break;
                }
            }
        }

        String json_string = GsonUtil.toJson(doctor);
        if (json_string.length() > 0 && add){
            FileUtil.writeData(fileName, json_string, true);
        }

        return add;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);//传入json数据代表的那个list
        List<Doctor> doctorList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                doctorList.add((Doctor) o);
            }
        }

        for (Doctor doctor1 : doctorList){
            if (doctor1.getId().equals(doctor.getId())){
                doctorList.set(doctorList.indexOf(doctor1), doctor);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Doctor doctor_temp : doctorList){
            if (doctorList.indexOf(doctor_temp) == doctorList.size() - 1){
                data.append(GsonUtil.toJson(doctor_temp));
            }
            else
                data.append(GsonUtil.toJson(doctor_temp)).append("\n");
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public void deleteDoctor(Doctor doctor) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);//传入json数据代表的那个list
        List<Doctor> doctorArrayList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                doctorArrayList.add((Doctor) o);
            }
        }

        for (Doctor doctor1 : doctorArrayList){
            if (doctor1.getId().equals(doctor.getId())){
                doctorArrayList.remove(doctor1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Doctor doctor_temp : doctorArrayList){
            if (doctorArrayList.indexOf(doctor_temp) == doctorArrayList.size() - 1){
                data.append(GsonUtil.toJson(doctor_temp));
            }
            else
                data.append(GsonUtil.toJson(doctor_temp)).append("\n");
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public List<Doctor> findAllDoctor() throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);
        List<Doctor> list1 = new ArrayList<>();
        for (Object o : list){
            list1.add((Doctor) o);
        }
        return list1;//返回一个列表，里面有所有的Doctor对象
    }

    @Override
    public Doctor findDoctorByID(String id) throws IOException {
        Doctor doctor_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);
        List<Doctor> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Doctor) o);
        }

        for (Doctor doctor : list1){
            if (doctor.getId().equals(id)){
                doctor_found = doctor;
            }
        }
        return doctor_found;//返回寻找到的Doctor对象
    }

    public Doctor findDoctorByName(String name) throws IOException{
        Doctor doctor_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);
        List<Doctor> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Doctor) o);
        }

        for (Doctor doctor : list1){
            if (doctor.getName().equals(name)){
                doctor_found = doctor;
            }
        }
        return doctor_found;//返回寻找到的Doctor对象
    }

    @Override
    public Doctor findDoctorByLoginName(String loginName) throws IOException {
        Doctor doctor_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Doctor.class);
        List<Doctor> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Doctor) o);
        }

        for (Doctor doctor : list1){
            if (doctor.getLoginName().equals(loginName)){
                doctor_found = doctor;
            }
        }
        return doctor_found;//返回寻找到的Doctor对象
    }
}
