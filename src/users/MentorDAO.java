package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import school.GroupModel;


public class MentorDAO extends UsersDAO {


    private final String DEFAULT_TABLE = Table.MENTORS.getName();
    private DbManagerDAO daoManager = new DbManagerDAO();


    private final Integer ID_INDEX = 0;
    private final Integer EMAIL_INDEX = 1;
    private final Integer FIRST_NAME_INDEX = 2;
    private final Integer LAST_NAME_INDEX = 3;
    private final Integer PASSWORD_INDEX = 4;


    public List<MentorModel> getObjects(List<String[]> dataCollection) {

        ArrayList<MentorModel> mentors = new ArrayList<MentorModel>();

        for (String [] record : dataCollection) {
            MentorModel mentor = getObject(record);
            mentors.add(mentor);
        }
        return mentors;
    }

    public MentorModel getObject(String[] record) {
        int id = Integer.parseInt(record[ID_INDEX]);
        String firstName = record[FIRST_NAME_INDEX];
        String lastName = record[LAST_NAME_INDEX];
        String email = record[EMAIL_INDEX];
        String password = record[PASSWORD_INDEX];
        GroupModel group = new GroupModel("undified");

        return new MentorModel(id, firstName, lastName, email, password, group);
    }

    public void saveObject(MentorModel mentor) {
        String mentor_id = String.valueOf(mentor.getId());
        String firstName = mentor.getFirstName();
        String lastName = mentor.getLastName();
        String email = mentor.getEmail();
        String password = mentor.getPassword();

        String query = String.format("UPDATE %s SET first_name=%s , last_name=%s, email=%s, password=%s " +
                "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, mentor_id);

        daoManager.inputData(query);
    }

    public void saveObjects(List<MentorModel> mentors) {

        for(MentorModel mentor : mentors) {
            saveObject(mentor);
        }
    }

}