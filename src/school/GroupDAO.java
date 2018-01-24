package school;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class GroupDAO {

    private final String DEFAULT_TABLE = Table.GROUPS.getName();

    public List<GroupModel> getObjects(String query) {

        List<GroupModel> groups = new ArrayList<GroupModel>();

        /// impl

        return groups;
    }

    public GroupModel getObject(String query){

        // temporary impl
        return new GroupModel(200, "a", new ArrayList<StudentModel>());
    }

    public void saveObject(GroupModel group){

        //
    }

    public void saveObjects(List<GroupModel> groups){

        //
    }
}
