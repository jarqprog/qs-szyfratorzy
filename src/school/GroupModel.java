package school;

import java.util.ArrayList;
import java.util.List;
import users.StudentModel;


public class GroupModel extends StudentSets {

    public GroupModel(int id, String name, List<StudentModel> students) {
        super(id, name, students);
    }

    public GroupModel(String name) {
        super(name);
        this.students = new ArrayList<StudentModel>();
        GroupDAO dao = new GroupDAO();
        dao.saveObject(this);
    }
}