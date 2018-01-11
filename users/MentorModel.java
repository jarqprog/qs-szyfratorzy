package users;

public class MentorModel extends UserModel
{
    Character groupName;

    public MentorModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        super.setUserRole("mentor");
        this.groupName = '0';
    }

    public MentorModel(int id, String firstName, String lastName, String password, Character groupName)
    {
        super(id, firstName, lastName, password);
        super.setUserRole("mentor");
        this.groupName = groupName;
    }

    public Character getMentorGroupName()
    {
        return groupName;
    }

    public void setMentorGroupName(Character newGroupName)
    {
        groupName = newGroupName;
    }
    public String toString()
    {
        return super.toString()+String.format(" Group name: %s",this.getMentorGroupName());
    }

}
