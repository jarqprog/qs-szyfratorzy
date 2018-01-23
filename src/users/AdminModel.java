package users;

public class AdminModel extends UserModel {

    public AdminModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        setRole("admin");
    }

    public AdminModel(int id, String firstName, String lastName, String email, String password)
    {
        super(id, firstName, lastName, email, password);
        setRole("admin");
    }
}
