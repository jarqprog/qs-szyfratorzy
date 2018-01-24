package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;


public class StudentDAO extends UsersDAO {

    private final String DEFAULT_TABLE = Table.STUDENTS.getName();

    public List<StudentModel> getObjects(String query) {

        List<StudentModel> students = new ArrayList<StudentModel>();
        /// impl

        return students;
    }

    public StudentModel getObject(String query){

        // temporary impl
        return new StudentModel(301, "Ania", "Pierog",
                "misiaczek@gmail.com", "12321", 'a');
    }

    public void saveObject(StudentModel student){

        //
    }

    public void saveObjects(List<StudentModel> students){

        //
    }
}
