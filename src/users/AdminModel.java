package users;

import application.Role;

public class AdminModel extends UserModel {


    public AdminModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        role = Role.ADMIN.getName();
        AdminDAO dao = new AdminDAO();
        dao.saveObject(this);
    }

    public AdminModel(int id, String firstName, String lastName, String email, String password)
    {
        super(id, firstName, lastName, email, password);
        role = Role.ADMIN.getName();
    }

    public String toString() {
         return super.toString();
    }

}
