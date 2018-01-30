package school;

import java.util.ArrayList;
import java.util.List;

import users.StudentDAO;
import users.StudentModel;


public class GroupModel extends StudentSets {

    public GroupModel(int id, String name, List<StudentModel> students) {
        super(id, name, students);
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

}