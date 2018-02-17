package model;

import dao.StudentDAO;

public class Group extends StudentSets {

    public Group(int id, String name) {
        super(id, name);
    }

    public Group(String name) {
        super(name);
    }

    public void setStudents() {
        StudentDAO dao = new StudentDAO();
        final String query = String.format("SELECT * FROM students WHERE group_id=%s;", id);
        this.students = dao.getManyObjects(query);
    }

}