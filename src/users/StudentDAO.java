package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;

public class StudentDAO extends UsersDAO {

    private final String DEFAULT_TABLE = Table.STUDENTS.getName();

    public List<StudentModel> getAllStudents(){
        List<StudentModel> students = new ArrayList<StudentModel>();
        /// impl
        return students;
    }

    public StudentModel getStudentById(int id){

        // temporary impl
        return new StudentModel(301, "Ania", "Pierog",
                "misiaczek@gmail.com", "12321", 'a');
    }
}
