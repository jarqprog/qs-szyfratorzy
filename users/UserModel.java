package users;

public class UserModel
{
    private static int counter;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public UserModel(String firstName, String lastName, String email, String password)
    {
        id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        role = "undefined";
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
}