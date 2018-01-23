package users;

public abstract class UserModel
{
    private static int counter;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private UsersDAO usersDao;

    public UserModel(String firstName, String lastName, String password) {
        usersDao = new UsersDAO();
        counter = usersDao.loadLastId("DataFiles/maxUserId.csv");
        id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        email = firstName + Integer.toString(id) + "@cc.com";
        this.password = password;
        role = "undefined";
        usersDao.saveLastId(counter, "DataFiles/maxUserId.csv");
    }

    public UserModel(int id, String firstName, String lastName, String email, String password)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        role = "undefined";
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String toString() {
        return String.format("Role: %s, Id: %s, First name: %s, Last name: %s, email: %s",
                            this.role, this.id, this.firstName, this.lastName, this.email);
    }
}
