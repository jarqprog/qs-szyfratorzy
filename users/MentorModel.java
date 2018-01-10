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
    public char getMentorGroupName()
    {
        return groupName;
    }

    public void setMentorGroupName(char newGroupName)
    {
        groupName = newGroupName;
    }
}
