package users;

public class MentorModel extends UserModel {

    public MentorModel(int id, String fname, String lname, String email, String password, String groupName)
    {
    super(id, firstName, lastName, email, password);
    this.groupName = groupName;
    }

    public String getMentorGroupName()
    {
        return groupName;
    }

    public void setMentorGroupName(String newGroupName)
    {
        groupName = newGroupName;
    }
}
