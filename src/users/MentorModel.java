package users;

import application.Role;

public class MentorModel extends UserModel
{
    String groupName;

    public MentorModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        role = Role.MENTOR.getName();
        this.groupName = "0";
    }

    public MentorModel(int id, String firstName, String lastName, String email, String password, String groupName)
    {
        super(id, firstName, lastName, email, password);
        role = Role.MENTOR.getName();
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String newGroupName)
    {
        groupName = newGroupName;
    }
    public String toString()
    {
        return super.toString()+String.format(" Group name: %s",this.getGroupName());
    }

}
