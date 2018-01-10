package users;

public class AdminModel extends UserModel {

    public AdminModel(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
        super.setRole("admin");
    }
}
