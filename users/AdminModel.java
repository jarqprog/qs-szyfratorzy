package users;

public class AdminModel extends UserModel {

    public AdminModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        super.setUserRole("admin");
    }
}
