package Model;

import application.Role;
import dao.AdminDAO;

public class Admin extends User {


    public Admin(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        role = Role.ADMIN.getName();
        this.id = saveNewObjectGetId();
    }

    public Admin(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
        role = Role.ADMIN.getName();
    }

    public void saveObject(){
        AdminDAO dao = new AdminDAO();
        dao.saveObject(this);
    }

    public int saveNewObjectGetId(){
        AdminDAO dao = new AdminDAO();
        return dao.saveObjectAndGetId(this);
    }
}
