package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;


public class StudentDAO {

    private final String DEFAULT_TABLE = Table.STUDENTS.getName();
    private final Integer IdIndex = 0;
    private final Integer firstNameIndex = 1;
    private final Integer lastNameIndex = 2;
    private final Integer emailIndex = 3;
    private final Integer passwordIndex = 4;
    private final Integer groupIndex = 5;

    private DbManagerDAO daoMenager = new DbManagerDAO();


    public List<StudentModel> getObjects(String query) {

        List<StudentModel> students = new ArrayList<StudentModel>();
        String sqlStatement = String.format("SELECT * FROM %s %s", DEFAULT_TABLE, query);
        List<String[]> data = daoMenager.getData(sqlStatement);

        for (String[] record: data){
            studentId = Integer.parseInt(record[idIndex]);
            firstName = record[firstNameIndex];
            lastName = record[lastNameIndex];
            email = record[emailIndex]);
            password = record[passwordIndex];
            group = record[groupIndex];

            students.add(new StudentModel(student_id))

        }

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
