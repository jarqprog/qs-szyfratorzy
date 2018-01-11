package users;

public class AdminModel extends UserModel {

    public AdminModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        setUserRole("admin");
    }

    public AdminModel(int id, String firstName, String lastName, String password)
    {
        super(id, firstName, lastName, password);
        setUserRole("admin");
    }
}
