package dao;

import managers.DbProcessManager;
import model.*;
import enums.Table;

import java.sql.Connection;
import java.sql.SQLException;


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

    public boolean save(Student student){
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
                    "VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)", DEFAULT_TABLE);
        } else {
            query = String.format(
                    "UPDATE %s SET first_name=?, last_name=?, email=?, password=?, wallet=?, " +
                            "experience=?, team_id=?, group_id=? " +
                            "WHERE id=?", DEFAULT_TABLE);
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, wallet);
            preparedStatement.setInt(6, experience);
            preparedStatement.setInt(7, teamId);
            preparedStatement.setInt(8, groupId);
            if(!studentId.equals("-1")) {
                preparedStatement.setInt(9, Integer.valueOf(studentId));
            }
            DbProcessManager.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.STUDENTS.getName();
    }

}