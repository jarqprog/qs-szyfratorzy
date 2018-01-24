package school;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class TeamDAO {

    private final String DEFAULT_TABLE = Table.GROUPS.getName();

    public List<TeamModel> getObjects(String query) {

        List<TeamModel> teams = new ArrayList<TeamModel>();

        /// impl

        return teams;
    }

    public TeamModel getObject(String query){

        // temporary impl
        return new TeamModel(200, "a", new ArrayList<StudentModel>());
    }

    public void saveObject(TeamModel team){

        //
    }

    public void saveObjects(List<TeamModel> teams){

        //
    }
}
