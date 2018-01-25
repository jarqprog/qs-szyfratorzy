package school;


import java.util.ArrayList;
import java.util.List;
import users.StudentModel;


public class TeamModel extends StudentSets{

    public TeamModel(int id, String name, List<StudentModel> students) {
        super(id, name, students);
    }

    public TeamModel(String name) {
        super(name);
        this.students = new ArrayList<StudentModel>();
    }



}
