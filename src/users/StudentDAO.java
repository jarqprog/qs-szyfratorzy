package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import item.ArtifactModel;
import school.GroupModel;


public class StudentDAO extends UsersDAO {

    private DbManagerDAO daoMenager = new DbManagerDAO();
    GroupModel group = new GroupModel("A");
    ArtifactModel artifact = new ArtifactModel("Topór",
            "Topór jest zajebisty", 100);


    private final String DEFAULT_TABLE = Table.STUDENTS.getName();
    private final Integer idIndex = 0;
    private final Integer firstNameIndex = 1;
    private final Integer lastNameIndex = 2;
    private final Integer emailIndex = 3;
    private final Integer passwordIndex = 4;
    private final Integer groupIndex = 5;

    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private char groupName;


    public List<StudentModel> getManyObjects(List<String[]> dataCollection) {

        List<StudentModel> students = new ArrayList<StudentModel>();
        for (String[] record : dataCollection) {
            StudentModel student = getOneObject(record);
            students.add(student);
        }
        return students;
    }

    public StudentModel getOneObject(String[] studentData) {

        studentId = Integer.parseInt(studentData[idIndex]);
        firstName = studentData[firstNameIndex];
        lastName = studentData[lastNameIndex];
        email = studentData[emailIndex];
        password = studentData[passwordIndex];
        groupName = studentData[groupIndex].charAt(0);

        return new StudentModel(studentId, firstName, lastName, email, password, groupName);
    }

    public void saveObject(StudentModel student) {

    }

    public void saveObjects(List<StudentModel> students) {

        //
    }
}