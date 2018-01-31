package school;

import java.util.ArrayList;
import users.StudentDAO;


public class GroupModel extends StudentSets {

    public GroupModel(int id, String name) {
        super(id, name);
        this.students = new ArrayList<>();
    }

    public GroupModel(String name) {
        super(name);
        this.students = new ArrayList<>();
        this.id = saveNewObjectGetId();
    }

    public int saveNewObjectGetId(){
        GroupDAO dao = new GroupDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void setStudents() {
        StudentDAO dao = new StudentDAO();
        final String query = String.format("SELECT * FROM students WHERE group_id=%s;", id);
        this.students = dao.getManyObjects(query);
    }

    public void saveObject(){
        GroupDAO dao = new GroupDAO();
        dao.saveObject(this);
    }

}