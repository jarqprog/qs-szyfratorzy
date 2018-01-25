package users;

import java.util.ArrayList;
import java.util.List;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;
import school.GroupModel;



public class MentorDAO extends FactoryDAO {

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
    private int groupId;

    public MentorDAO(){
        this.DEFAULT_TABLE = Table.MENTORS.getName();
    }

    public List<MentorModel> getManyObjects(List<String[]> dataCollection) {
        List<MentorModel> mentors = new ArrayList<>();
        for (String [] record : dataCollection) {
            MentorModel mentor = getOneObject(record);
            mentors.add(mentor);
        }
        return mentors;
    }

    public List<MentorModel> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public MentorModel getOneObject(String[] record) {
        mentorId = Integer.parseInt(record[ID_INDEX]);
        firstName = record[FIRST_NAME_INDEX];
        lastName = record[LAST_NAME_INDEX];
        email = record[EMAIL_INDEX];
        password = record[PASSWORD_INDEX];
        group = new GroupModel(1, "undefined", new ArrayList<>());

        return new MentorModel(mentorId, firstName, lastName, email, password, group);
    }

    public MentorModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }

    public void saveObject(MentorModel mentor) {
        String mentorId = String.valueOf(mentor.getId());
        firstName = mentor.getFirstName();
        lastName = mentor.getLastName();
        email = mentor.getEmail();
        password = mentor.getPassword();
        groupId = mentor.getGroup().getId();
        String query;

        if(mentorId.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s VALUES('%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, firstName, lastName, password, groupId);
        } else {
            query = String.format("UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', group_id=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, groupId, mentorId);
            dao = new DbManagerDAO();
            dao.inputData(query);
        }
    }

    public void saveObjects(List<MentorModel> mentors) {

        for(MentorModel mentor : mentors) {
            saveObject(mentor);
        }
    }

}