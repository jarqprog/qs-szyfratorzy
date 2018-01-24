package school;

import users.StudentModel;

public class GroupController {

    GroupModel group;

    public GroupController(GroupModel group){

        this.group = group;

    }

    public void addStudent(StudentModel student){

        // implementation to update database!!!

        group.addStudent(student);
    }

    public void removeStudent(StudentModel student){

        // implementation to update database!!!

        group.removeStudent(student);
    }
}
