package model;

public class Group extends StudentSet {

    Group(int id, String name) {
        super(id, name);
    }

    Group(String name) {
        super(name);
    }

    public void setStudents() {
        this.students = ModelDaoFactory.getByType(StudentDAO.class)
                .getFilteredModelsByIntegerParameter("group_id", id);
    }
}