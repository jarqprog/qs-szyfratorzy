package users;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;

public class MentorDAO {


    private final String DEFAULT_TABLE = Table.MENTORS.getName();

    public List<MentorModel> getObjects(String query){

        List<MentorModel> mentors = new ArrayList<MentorModel>();
        ///

        return mentors;
    }

    public MentorModel getObject(String query){

        // temporary

        return new MentorModel(201, "Marian", "Nowak", "nowak@cc.pl", "12321", 'a');
    }

    public void saveObject(MentorModel mentor){

        //
    }

    public void saveObjects(List<MentorModel> mentors){

        //
    }

}
