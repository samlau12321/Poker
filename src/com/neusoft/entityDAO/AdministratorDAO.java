package com.neusoft.entityDAO;

import com.neusoft.entity.people.Administrator;

import java.io.IOException;
import java.util.List;

public interface AdministratorDAO {
    public boolean addAdministrator(Administrator administrator) throws IOException;

    public void updateAdministrator(Administrator administrator) throws IOException;

    public void deleteAdministrator(Administrator administrator) throws IOException;

    public List<Administrator> findAllAdministrator() throws IOException;

    public Administrator findAdministratorByID(String id) throws IOException;

    public Administrator findAdminByName(String name) throws IOException;

    public Administrator findAdminByLoginName(String name) throws IOException;
}
