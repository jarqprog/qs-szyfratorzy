package users;

public class MentorModel extends UserModel
{
    Character groupName;

    public MentorModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        super.setRole("mentor");
        this.groupName = '0';
    }

    public MentorModel(int id, String firstName, String lastName, String email, String password, Character groupName)
    {
        super(id, firstName, lastName, email, password);
        super.setRole("mentor");
        this.groupName = groupName;
    }

    public Character getGroupName()
    {
        return groupName;
    }

    public void setGroupName(Character newGroupName)
    {
        groupName = newGroupName;
    }
    public String toString()
    {
        return super.toString()+String.format(" Group name: %s",this.getGroupName());
    }

}
