package users;

import application.Role;
import school.GroupModel;

public class MentorModel extends UserModel {
    private GroupModel group;

    public MentorModel(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.group = new GroupModel("undefined");
        role = Role.MENTOR.getName();
    }

    public MentorModel(int id, String firstName, String lastName,
                       String email, String password, GroupModel group) {
        super(id, firstName, lastName, email, password);
        this.group = group;
        role = Role.MENTOR.getName();
    }

    public GroupModel getGroup()
    {
        return group;
    }

    public void setGroupName(GroupModel group)
    {
        this.group = group;
    }

//    public String toString()
//    {
//        return super.toString()+String.format(" Group name: %s",this.getGroupName());
//    }

}
