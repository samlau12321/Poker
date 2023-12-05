package com.neusoft.service.Impl;

import com.neusoft.entity.people.Administrator;
import com.neusoft.entityDAO.impl.AdministratorDAOImpl;
import com.neusoft.service.AdministratorService;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private static AdministratorServiceImpl administratorService = null;

    private AdministratorServiceImpl(){

    }

    public static AdministratorServiceImpl getAdministratorService(){
        if (administratorService == null){
            administratorService = new AdministratorServiceImpl();
        }

        return administratorService;
    }


    AdministratorDAOImpl administratorDAO = AdministratorDAOImpl.getAdministratorDAO();

    @Override
    public Administrator showSpecialPeople(Administrator administrator) throws IOException {
        return administratorDAO.findAdministratorByID(administrator.getId());
    }

    @Override
    public Administrator findPeopleByName(String name) throws IOException {
        List<Administrator> list = administratorDAO.findAllAdministrator();
        Administrator administrator = new Administrator();
        for (Administrator a : list){
            if (a.getName().equals(name)){
                administrator = a;
            }
        }
        return administrator;
    }

    @Override
    public boolean resignUser(String name, int age,String sex, String ID, String loginName, String password, String telephoneNumber) throws IOException {
        boolean judge = false;

        Administrator administrator = new Administrator(name, age, sex, ID, loginName, password, telephoneNumber);

        judge = administratorDAO.addAdministrator(administrator);
//        String s = GsonUtil.toJson(administrator);
//        if (s.length() > 0){
//            judge = true;
//            FileUtil.writeData("Administrators.json", s, true);
//        }
        return judge;
    }

    @Override
    public void maintenanceInformation(Object object) {

    }

    @Override
    public void save(Object obj, String filename, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, filename, mode);
    }

    @Override
    public List<Object> queryAll(String filename) throws IOException {
        List<Object> list =FileUtil.readDataToList(filename, Object.class);
        return list;
    }

    public boolean validate(String loginName, String password, String filename) throws IOException {
        boolean login = false;
        List<Administrator> administratorList;
        administratorList = administratorDAO.findAllAdministrator();
        for (Administrator administrator : administratorList){
            if (administrator.getLoginName().equals(loginName) && administrator.getPassword().equals(password)){
                login = true;
                break;
            }
        }
        return login;
    }

}
