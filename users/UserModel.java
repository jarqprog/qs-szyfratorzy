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

    public UserModel(String firstName, String lastName, String password)
    {
        id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        email = firstName + Integer.toString(id) + "@cc.com";
        this.password = password;
        role = "undefined";
    }

    public int getUserID()
    {
        return id;
    }

    public String getUserFirstName()
    {
        return firstName;
    }

    public void setUserFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getUserLastName()
    {
        return lastName;
    }

    public void setUserLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserEmail()
    {
        return email;
    }

    public void setUserEmail(String email)
    {
        this.email = email;
    }

    public String getUserPassword()
    {
        return password;
    }

    public void setUserPassword(String password)
    {
        this.password = password;
    }

    public String getUserRole()
    {
        return role;
    }

    public void setUserRole(String role)
    {
        this.role = role;
    }
}