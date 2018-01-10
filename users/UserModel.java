package users;

public class UserModel
{
    private static int counter;
    private int iD;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserModel()
    {
        iD = counter++; 
    }

    public int getUserID()
    {
        return iD;
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
}