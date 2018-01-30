package school;


import java.util.ArrayList;
import java.util.List;

import users.StudentDAO;
import users.StudentModel;

public class TeamModel extends StudentSets{

    public TeamModel(int id, String name, List<StudentModel> students) {
        super(id, name, students);
    }

    public TeamModel(String name) {
        super(name);
        this.students = new ArrayList<>();
        this.id = saveNewObjectGetId();
    }

    public int saveNewObjectGetId(){
        TeamDAO dao = new TeamDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void setStudents() {
        StudentDAO dao = new StudentDAO();
        final String query = String.format("SELECT * FROM students WHERE team_id=%s;", id);
        this.students = dao.getManyObjects(query);
    }
}
