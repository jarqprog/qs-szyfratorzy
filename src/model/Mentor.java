package model;

import enums.Role;

public class Mentor extends User {

    private Group group;

    Mentor(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        group = new Group(1,"undefined");
        role = Role.MENTOR.getName();
    }

    public Mentor(int id, String firstName, String lastName,
                       String email, String password, Group group) {
        super(id, firstName, lastName, email, password);
        this.group = group;
        role = Role.MENTOR.getName();

    }

    public Group getGroup() {
        setGroup();
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        saveModel();
    }

    public void setGroup() {
        this.group.setStudents();
    }

    public String toString()
    {
        return super.toString() + String.format("\tGroup name: %s", group.getName());
     }

    public String getFullDataToString() {
        return super.getFullDataToString() + String.format(
                "\t -group: %s\n", getGroup());
    }
}
