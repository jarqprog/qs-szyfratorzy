package dao;

import managers.ResultSetManager;
import model.*;
import enums.Table;

import java.sql.Connection;


public class StudentDAO extends ActiveModelDAOImpl<Student> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int wallet;
    private int experience;
    private int groupId;
    private int teamId;

    StudentDAO(Connection connection) {
        super(connection);
    }

    public Student extractModel(String[] studentData) {

        final Integer ID_INDEX = 0;
        final Integer FIRST_NAME_INDEX = 1;
        final Integer LAST_NAME_INDEX = 2;
        final Integer EMAIL_INDEX = 3;
        final Integer PASSWORD_INDEX = 4;
        final Integer WALLET_INDEX = 5;
        final Integer EXPERIENCE_INDEX = 6;
        final Integer TEAM_INDEX = 7;
        final Integer GROUP_INDEX = 8;


        int studentId = Integer.parseInt(studentData[ID_INDEX]);
        firstName = studentData[FIRST_NAME_INDEX];
        lastName = studentData[LAST_NAME_INDEX];
        email = studentData[EMAIL_INDEX];
        password = studentData[PASSWORD_INDEX];
        wallet = Integer.parseInt(studentData[WALLET_INDEX]);
        experience  = Integer.parseInt(studentData[EXPERIENCE_INDEX]);
        teamId = Integer.parseInt(studentData[TEAM_INDEX]);
        groupId = Integer.parseInt(studentData[GROUP_INDEX]);


        Team team = DaoFactory.getByType(TeamDAO.class).getModelById(teamId);


        Group group = DaoFactory.getByType(GroupDAO.class).getModelById(groupId);

        return new Student(studentId, firstName, lastName, email, password, wallet, experience,
                team, group);
    }

    public void save(Student student){
        String studentId = String.valueOf(student.getId());
        firstName = student.getFirstName();
        lastName = student.getLastName();
        email = student.getEmail();
        password = student.getPassword();
        wallet = student.getWallet();
        experience = student.getExperience();
        teamId = student.getTeam().getId();
        groupId = student.getGroup().getId();
        String query;

        if(studentId.equals("-1")){

            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', '%s', %s, %s, %s, %s);",
                    DEFAULT_TABLE, firstName, lastName, email, password, wallet,
                    experience, teamId, groupId);

        } else{

            query = String.format(
                            "UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', " +
                            " wallet=%s, experience=%s, team_id=%s, group_id=%s " +
                            "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, wallet, experience,
                            teamId, groupId, studentId);
        }

        dao = new ResultSetManager();
        dao.inputData(query);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.STUDENTS.getName();
    }

}