package users;

public class MentorModel extends UserModel 
{
    char groupName;

    public MentorModel(String firstName, String lastName, String email, String password, char groupName)
    {
        super(firstName, lastName, email, password);
        super.setRole("mentor");
        this.groupName = groupName;
    }

    public char getMentorGroupName()
    {
        return groupName;
    }

    public void setMentorGroupName(char newGroupName)
    {
        groupName = newGroupName;
    }
}
