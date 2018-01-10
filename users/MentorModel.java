package users;

public class MentorModel extends UserModel
{
    char groupName;

    public MentorModel(String firstName, String lastName, String password, char groupName)
    {
        super(firstName, lastName, password);
        super.setUserRole("mentor");
        this.groupName = groupName;
    }

    public MentorModel(int id, String firstName, String lastName, String email, String password, char groupName)
    {
        super(id, firstName, lastName, email, password);
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
