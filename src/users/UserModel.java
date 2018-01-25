package users;

public abstract class UserModel
{
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String role;
    private UsersDAO usersDao;

    public UserModel(int id, String firstName, String lastName, String email, String password) {
        this(firstName, lastName, password);
        this.id = id;
        this.email = email;
    }

    public UserModel(String firstName, String lastName, String password) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        email = firstName.toLowerCase() + lastName.toLowerCase() + "@cc.com";
        this.password = password;
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
