package users;

public class MentorModel extends UserModel
{
    char groupName;

    public MentorModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        super.setUserRole("mentor");
        this.groupName = '0';
    }

    public MentorModel(int id, String firstName, String lastName, String password, char groupName)
    {
        super(id, firstName, lastName, password);
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
