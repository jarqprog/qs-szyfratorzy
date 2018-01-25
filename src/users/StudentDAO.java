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
    private int groupId;
    private int teamId;
    List<ArtifactModel> inventory;


    public List<StudentModel> getManyObjects(List<String[]> dataCollection) {

        List<StudentModel> students = new ArrayList<StudentModel>();
        for (String[] record : dataCollection) {
            StudentModel student = getOneObject(record);
            students.add(student);
        }
        return students;
    }

    public List<StudentModel> getManyObjects(String query) {
        DbManagerDAO dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
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
        team = new TeamModel("undefined"); // tmp
        group = new GroupModel("undefined"); // tmp

        inventory = new ArrayList<>();

        return new StudentModel(studentId, firstName, lastName, email, password, wallet, experience, attendance,
                team, group, inventory);
    }

    public StudentModel getOneObject(String query) {
        DbManagerDAO dao = new DbManagerDAO();
        String[] studentData = dao.getData(query).get(0);
        studentId = Integer.parseInt(studentData[ID_INDEX]);
        firstName = studentData[FIRST_NAME_INDEX];
        lastName = studentData[LAST_NAME_INDEX];
        email = studentData[EMAIL_INDEX];
        password = studentData[PASSWORD_INDEX];
        wallet = Integer.parseInt(studentData[WALLET_INDEX]);
        experience  = Integer.parseInt(studentData[EXPERIENCE_INDEX]);
        attendance = Float.parseFloat(studentData[ATTENDANCE_INDEX]);
        team = new TeamModel("undefined"); // tmp
        group = new GroupModel("undefined"); // tmp
        inventory = new ArrayList<>();

        return new StudentModel(studentId, firstName, lastName, email, password, wallet, experience, attendance,
                team, group, inventory);
    }

    public void saveObject(StudentModel student) {
        String studentId = String.valueOf(student.getId());
        firstName = student.getFirstName();
        lastName = student.getLastName();
        email = student.getEmail();
        password = student.getPassword();
        wallet = student.getWallet();
        experience = student.getExperience();
        attendance = student.getAttendance();
        teamId = student.getTeam().getId();
        groupId = student.getGroup().getId();

        String query;
        if(studentId.equals("-1")){

            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', '%s', %s, %s, %s, %s, %s);",
                    DEFAULT_TABLE, firstName, lastName, email, password, wallet,
                    experience, attendance, teamId, groupId);

        } else{

            query = String.format(
                            "UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', " +
                            " wallet=%s, experience=%s, attendance=%s, team_id=%s, group_id=%s " +
                            "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, wallet, experience,
                    attendance, studentId, teamId, groupId);
        }

        daoManager = new DbManagerDAO();
        daoManager.inputData(query);
    }

    public void saveObjects(List<StudentModel> students) {

        for(StudentModel student : students) {
            saveObject(student);
        }
    }
}