package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;

public class MentorDAO extends UsersDAO {


    private final String DEFAULT_TABLE = Table.MENTORS.getName();
    private final Integer idIndex = 0;
    private final Integer firstNameIndex = 1;
    private final Integer lastNameIndex = 2;
    private final Integer emailIndex = 3;
    private final Integer passwordIndex = 4;
    private final Integer groupIndex = 5;

//    private DbManagerDAO daoManager = new DbManagerDAO();


//    public List<MentorModel> getObjects(String query) {
//
//    }
//
//        List<MentorModel> mentors = new ArrayList<MentorModel>();
//        String sqlStatement = String.format("SELECT * FROM %s %s", DEFAULT_TABLE, query);
//        List<String[]> data = daoManager.getData(sqlStatement);
//
//        for (String[] record : data){
//            int mentorId = Integer.parseInt(record[idIndex]);
//            String firstName = record[firstNameIndex];
//            String lastName = record[lastNameIndex];
//            String email = record[emailIndex];
//            String password = record[passwordIndex];
//            String group = record[groupIndex];
//
//            mentors.add(new MentorModel(mentorId, firstName, lastName, email, password, group));
//        }
//        return mentors;
//    }
//
//    public MentorModel getObject(String query){
//
//        // temporary
//
//        return new MentorModel(201, "Marian", "Nowak", "nowak@cc.pl", "12321", "a");
//    }
//
//    public void saveObject(MentorModel mentor){
//
//        //
//    }
//
//    public void saveObjects(List<MentorModel> mentors){
//
//        //
//    }

}
