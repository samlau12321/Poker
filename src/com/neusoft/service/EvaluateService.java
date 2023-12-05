package com.neusoft.service;

import com.neusoft.entity.people.Doctor;

import java.util.Date;

public interface EvaluateService extends BaseService{
    public void showAllRecord();

    public void setEvaluator(Doctor doctor);

    public Date setTime(Date date);

    public void showPatientInfo();

    public void showResult();
}
