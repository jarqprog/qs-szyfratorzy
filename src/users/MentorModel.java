package users;

import application.Role;
import school.GroupModel;

public class MentorModel extends UserModel {

    private GroupModel group;

    public MentorModel(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        group = new GroupModel(1,"undefined");
        role = Role.MENTOR.getName();
        this.id = saveNewObjectGetId();
    }

    public MentorModel(int id, String firstName, String lastName,
                       String email, String password, GroupModel group) {
        super(id, firstName, lastName, email, password);
        this.group = group;
        role = Role.MENTOR.getName();

    }

    public GroupModel getGroup() {
        setGroup();
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
        saveObject();
    }

    public void setGroup() {
        this.group.setStudents();
    }

    public String toString()
    {
        return super.toString()+String.format(" Group name: %s", group.getName());
     }

    public String getFullDataToString() {
        return super.getFullDataToString() + String.format(
                " \n\t -group: %s\n", getGroup());
    }

    public void saveObject(){
        MentorDAO dao = new MentorDAO();
        dao.saveObject(this);
    }

    public int saveNewObjectGetId(){
        MentorDAO dao = new MentorDAO();
        return dao.saveObjectAndGetId(this);
    }

}
