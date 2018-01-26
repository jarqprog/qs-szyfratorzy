package users;

import application.Role;
import school.GroupModel;
import java.util.ArrayList;

public class MentorModel extends UserModel {

    private GroupModel group;

    public MentorModel(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        group = new GroupModel(1,"undefined", new ArrayList<>());
        role = Role.MENTOR.getName();
        MentorDAO dao = new MentorDAO();
        dao.saveObject(this);
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

    public void setGroup(GroupModel group)
    {
        this.group = group;
    }

    public String toString()
    {
        return super.toString()+String.format(" Group name: %s",group.getName());
     }

}
