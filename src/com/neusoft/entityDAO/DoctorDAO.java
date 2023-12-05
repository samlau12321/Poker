package com.neusoft.entityDAO;

import com.neusoft.entity.people.Doctor;

import java.io.IOException;
import java.util.List;

public interface DoctorDAO {
    public boolean addDoctor(Doctor doctor) throws IOException;

    public void updateDoctor(Doctor doctor) throws IOException;

    public void deleteDoctor(Doctor doctor) throws IOException;

    public List<Doctor> findAllDoctor() throws IOException;

    public Doctor findDoctorByID(String id) throws IOException;

    public Doctor findDoctorByName(String name) throws IOException;

    public Doctor findDoctorByLoginName(String loginName) throws IOException;
}
