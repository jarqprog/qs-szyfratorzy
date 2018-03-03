package model;

import dao.DaoFactory;
import dao.StudentDAO;

public class Group extends StudentSets {

    public Group(int id, String name) {
        super(id, name);
    }

    Group(String name) {
        super(name);
    }

    public void setStudents() {
        this.students = DaoFactory.getByType(StudentDAO.class)
                .getFilteredModelsByIntegerParameter("group_id", id);
    }
}