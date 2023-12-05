package com.neusoft.entityDAO.impl;

import com.neusoft.entity.people.Administrator;
import com.neusoft.entityDAO.AdministratorDAO;
import com.neusoft.exception.IDRepeatException;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAOImpl implements AdministratorDAO {
    private static AdministratorDAOImpl administratorDAO = null;
    private AdministratorDAOImpl(){

    }

    public static AdministratorDAOImpl getAdministratorDAO(){
        if (administratorDAO == null){
            administratorDAO = new AdministratorDAOImpl();
        }

        return administratorDAO;
    }

    String fileName = "Administrators.json";

    @Override
    public boolean addAdministrator(Administrator administrator) throws IOException {
        boolean add = true;
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);
        if (list != null){
            for(int i = 0 ;i < list.size(); i++){
                Administrator administrator1 =(Administrator) list.get(i);
                String id = administrator1.getId();
                if (id.equals(administrator.getId())){
                    add = false;
                    break;
                }
            }
        }

        String json_string = GsonUtil.toJson(administrator);
        if (json_string.length() > 0 && add){
            FileUtil.writeData(fileName, json_string, true);
        }

        return add;
    }

    @Override
    public void updateAdministrator(Administrator administrator) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);//传入json数据代表的那个list
        List<Administrator> administratorList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                administratorList.add((Administrator) o);
            }
        }

        for (Administrator administrator1 : administratorList){
            if (administrator1.getId().equals(administrator.getId())){
                administratorList.set(administratorList.indexOf(administrator1), administrator);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Administrator administrator_temp : administratorList){
            if (administratorList.indexOf(administrator_temp) == administratorList.size() - 1){
                data.append(GsonUtil.toJson(administrator_temp));
            }
            else
                data.append(GsonUtil.toJson(administrator_temp)).append("\n");
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public void deleteAdministrator(Administrator administrator) throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);//传入json数据代表的那个list
        List<Administrator> administratorList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                administratorList.add((Administrator) o);
            }
        }

        for (Administrator administrator1 : administratorList){
            if (administrator1.getId().equals(administrator.getId())){
                administratorList.remove(administrator1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Administrator administrator_temp : administratorList){
            if (administratorList.indexOf(administrator_temp) == administratorList.size() - 1){
                data.append(GsonUtil.toJson(administrator_temp));
            }
            else
                data.append(GsonUtil.toJson(administrator_temp)).append("\n");
        }
        FileUtil.writeData(fileName, data.toString(), false);
    }

    @Override
    public List<Administrator> findAllAdministrator() throws IOException {
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);
        List<Administrator> list1 = new ArrayList<>();
        for (Object o : list){
            list1.add((Administrator) o);
        }
        return list1;//返回一个列表，里面有所有的Administrator对象
    }

    @Override
    public Administrator findAdministratorByID(String id) throws IOException {
        Administrator administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);
        List<Administrator> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Administrator) o);
        }

        for (Administrator administrator : list1){
            if (administrator.getId().equals(id)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Administrator对象
    }

    @Override
    public Administrator findAdminByName(String name) throws IOException {
        Administrator administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);
        List<Administrator> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Administrator) o);
        }

        for (Administrator administrator : list1){
            if (administrator.getName().equals(name)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Administrator对象
    }

    @Override
    public Administrator findAdminByLoginName(String loginName) throws IOException {
        Administrator administrator_found = null;
        List<Object> list = FileUtil.readDataToList(fileName, Administrator.class);
        List<Administrator> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Administrator) o);
        }

        for (Administrator administrator : list1){
            if (administrator.getLoginName().equals(loginName)){
                administrator_found = administrator;
            }
        }
        return administrator_found;//返回寻找到的Administrator对象
    }
}
