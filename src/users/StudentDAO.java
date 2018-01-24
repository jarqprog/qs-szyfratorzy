package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import item.ArtifactModel;
import school.GroupModel;
import school.TeamModel;


public class StudentDAO extends UsersDAO {

    private DbManagerDAO daoManager;

    private final String DEFAULT_TABLE = Table.STUDENTS.getName();
    private final Integer ID_INDEX = 0;
    private final Integer FIRST_NAME_INDEX = 1;
    private final Integer LAST_NAME_INDEX = 2;
    private final Integer EMAIL_INDEX = 3;
    private final Integer PASSWORD_INDEX = 4;

    private final Integer WALLET_INDEX = 5;
    private final Integer EXPERIENCE_INDEX = 6;
    private final Integer ATTENDANCE_INDEX = 7;
    private final Integer TEAM_INDEX = 8;
    private final Integer GROUP_INDEX = 9;
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int wallet;
    private int experience;
    private float attendance;
    private GroupModel group;
    private TeamModel team;
    List<ArtifactModel> inventory;


    public List<StudentModel> getManyObjects(List<String[]> dataCollection) {

        List<StudentModel> students = new ArrayList<StudentModel>();
        for (String[] record : dataCollection) {
            StudentModel student = getOneObject(record);
            students.add(student);
        }
        return students;
    }

    public StudentModel getOneObject(String[] studentData) {

        studentId = Integer.parseInt(studentData[ID_INDEX]);
        firstName = studentData[FIRST_NAME_INDEX];
        lastName = studentData[LAST_NAME_INDEX];
        email = studentData[EMAIL_INDEX];
        password = studentData[PASSWORD_INDEX];
        wallet = Integer.parseInt(studentData[WALLET_INDEX]);
        experience  = Integer.parseInt(studentData[EXPERIENCE_INDEX]);
        attendance = Float.parseFloat(studentData[ATTENDANCE_INDEX]);
        group = new GroupModel("undefined"); // tmp
        team = new TeamModel("undefined"); // tmp
        inventory = new ArrayList<>();

        return new StudentModel(studentId, firstName, lastName, email, password, wallet, experience, attendance,
                team, group, inventory);
    }
    public void saveObject(StudentModel student) {

    }

    public void saveObjects(List<StudentModel> students) {
    }
}