package model;

import enums.Role;
import dao.AdminDAO;
import factory.ObjDaoFactory;

public class Admin extends User {


    public Admin(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        role = Role.ADMIN.getName();
    }

    public Admin(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
        role = Role.ADMIN.getName();
    }

//    public void saveObject(){
//        ObjDaoFactory.get(AdminDAO.class).save(this);
//    }
}
