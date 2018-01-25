package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import school.GroupModel;


public class MentorDAO extends UsersDAO {

    private DbManagerDAO daoManager;

    private final String DEFAULT_TABLE = Table.MENTORS.getName();
    private final Integer ID_INDEX = 0;
    private final Integer EMAIL_INDEX = 1;
    private final Integer FIRST_NAME_INDEX = 2;
    private final Integer LAST_NAME_INDEX = 3;
    private final Integer PASSWORD_INDEX = 4;

    private int mentorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private GroupModel group;

    public List<MentorModel> getManyObjects(List<String[]> dataCollection) {

        ArrayList<MentorModel> mentors = new ArrayList<MentorModel>();

        for (String [] record : dataCollection) {
            MentorModel mentor = getObject(record);
            mentors.add(mentor);
        }
        return mentors;
    }

    public MentorModel getObject(String[] record) {
        mentorId = Integer.parseInt(record[ID_INDEX]);
        firstName = record[FIRST_NAME_INDEX];
        lastName = record[LAST_NAME_INDEX];
        email = record[EMAIL_INDEX];
        password = record[PASSWORD_INDEX];
        group = new GroupModel("undefined");

        return new MentorModel(mentorId, firstName, lastName, email, password, group);
    }

    public void saveObject(MentorModel mentor) {
        String mentorId = String.valueOf(mentor.getId());
        firstName = mentor.getFirstName();
        lastName = mentor.getLastName();
        email = mentor.getEmail();
        password = mentor.getPassword();

        // dodaj grupę!!!

        String query;

        if(mentorId.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s VALUES('%s', '%s', '%s');",
                    DEFAULT_TABLE, firstName, lastName, password);
        } else {
            query = String.format("UPDATE %s SET first_name=%s , last_name=%s, email=%s, password=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, mentorId);
            daoManager = new DbManagerDAO();
            daoManager.inputData(query);
        }
    }

    public void saveObjects(List<MentorModel> mentors) {

        for(MentorModel mentor : mentors) {
            saveObject(mentor);
        }
    }

}