package users;

import java.util.ArrayList;
import java.util.List;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;
import school.GroupDAO;
import school.GroupModel;



public class MentorDAO extends FactoryDAO {

    private int mentorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private GroupModel group;
    private int groupId;

    public MentorDAO(){
        this.DEFAULT_TABLE = Table.MENTORS.getName();
    }

    public MentorModel getOneObject(String[] record) {

        final Integer ID_INDEX = 0;
        final Integer FIRST_NAME_INDEX = 1;
        final Integer LAST_NAME_INDEX = 2;
        final Integer EMAIL_INDEX = 3;
        final Integer PASSWORD_INDEX = 4;
        final Integer GROUP_INDEX = 5;

        mentorId = Integer.parseInt(record[ID_INDEX]);
        firstName = record[FIRST_NAME_INDEX];
        lastName = record[LAST_NAME_INDEX];
        email = record[EMAIL_INDEX];
        password = record[PASSWORD_INDEX];
        groupId = Integer.parseInt(record[GROUP_INDEX]);

        final String groupQuery = String.format("SELECT * FROM groups WHERE id=%s;", groupId);
        GroupDAO groupDAO = new GroupDAO();
        group = groupDAO.getOneObject(groupQuery);

        return new MentorModel(mentorId, firstName, lastName, email, password, group);
    }

    public MentorModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }

    public <T> void saveObject(T t){
        MentorModel mentor = (MentorModel) t;
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
        dao = new DbManagerDAO();
        dao.inputData(query);
    }
}