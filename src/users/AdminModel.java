package users;

import application.Role;

public class AdminModel extends UserModel {

    @Override
    public String toString() {
        return

                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\''
                ;
    }

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
}
