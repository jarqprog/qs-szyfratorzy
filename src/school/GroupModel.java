package school;

import users.StudentDAO;
import application.Box;


public class GroupModel extends StudentSets {

    public GroupModel(int id, String name) {
        super(id, name);
        this.students = new Box<>();
    }

    public GroupModel(String name) {
        super(name);
        this.students = new Box<>();
        this.id = saveNewObjectGetId();
    }

    public int saveNewObjectGetId(){
        GroupDAO dao = new GroupDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void setStudents() {
        StudentDAO dao = new StudentDAO();
        final String query = String.format("SELECT * FROM students WHERE group_id=%s;", id);
        this.students.set(dao.getManyObjects(query));
    }

    public void saveObject(){
        GroupDAO dao = new GroupDAO();
        dao.saveObject(this);
    }

}