package users;

public class MentorModel extends UserModel {
    char groupName;

    public MentorModel(int id, String firstName, String lastName, String email, String password, char groupName)
    {
    super(id, firstName, lastName, email, password);
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
