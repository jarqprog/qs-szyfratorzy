package dao;

import managers.TemporaryManager;
import model.Mentor;
import enums.Table;
import model.Group;

import java.sql.Connection;

public class MentorDAO extends ActiveModelDAOImpl<Mentor> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int groupId;

    MentorDAO(Connection connection) {
        super(connection);
    }

    public Mentor getOneObject(String[] record) {

        final Integer ID_INDEX = 0;
        final Integer FIRST_NAME_INDEX = 1;
        final Integer LAST_NAME_INDEX = 2;
        final Integer EMAIL_INDEX = 3;
        final Integer PASSWORD_INDEX = 4;
        final Integer GROUP_INDEX = 5;

        int mentorId = Integer.parseInt(record[ID_INDEX]);
        firstName = record[FIRST_NAME_INDEX];
        lastName = record[LAST_NAME_INDEX];
        email = record[EMAIL_INDEX];
        password = record[PASSWORD_INDEX];
        groupId = Integer.parseInt(record[GROUP_INDEX]);

        final String groupQuery = String.format("SELECT * FROM groups WHERE id=%s;", groupId);
        GroupDAO groupDAO = new GroupDAO(connection);
        Group group = groupDAO.getOneObject(groupQuery);

        return new Mentor(mentorId, firstName, lastName, email, password, group);
    }

    public void save(Mentor mentor){
        String mentorId = String.valueOf(mentor.getId());
        firstName = mentor.getFirstName();
        lastName = mentor.getLastName();
        email = mentor.getEmail();
        password = mentor.getPassword();
        groupId = mentor.getGroup().getId();

        String query;
        if (mentorId.equals("-1")) {
            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, firstName, lastName, email, password, groupId);
        } else {
            query = String.format("UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', group_id=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, groupId, mentorId);
        }
        dao = new TemporaryManager();
        dao.inputData(query);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.MENTORS.getName();
    }
}