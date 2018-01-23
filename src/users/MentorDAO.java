package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;

public class MentorDAO extends UsersDAO {


    private final String DEFAULT_TABLE = Table.MENTORS.getName();

    public List<MentorModel> getAllMentors(){
        List<MentorModel> mentors = new ArrayList<MentorModel>();
        ///
        return mentors;
    }

    public MentorModel getMentorById(int id){

        // temporary
        return new MentorModel(201, "Marian", "Nowak", "nowak@cc.pl", "12321", 'a');
    }

}
