package com.neusoft.service;

import com.neusoft.entity.people.Administrator;
import com.neusoft.entity.people.Patient;

import java.io.IOException;
import java.util.List;

public interface AdministratorService extends BaseService{
    public Administrator showSpecialPeople(Administrator administrator) throws IOException;

    public Administrator findPeopleByName(String name) throws IOException;

    public boolean resignUser(String name, int age,String sex, String ID, String loginName, String password, String telephoneNumber) throws IOException;

    public void maintenanceInformation(Object object);
}
