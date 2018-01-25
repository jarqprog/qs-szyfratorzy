package school;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class GroupDAO {

    private final String DEFAULT_TABLE = Table.GROUPS.getName();

    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;
    private final Integer STUD_LIST_INDEX = 2;

    public List<GroupModel> getObjects(List<String[]> dataCollection) {

        List<GroupModel> groups = new ArrayList<GroupModel>();

        for (String [] record : dataCollection) {
            GroupModel group = getObject(record);
            groups.add(group);
        }

        return groups;
    }

    public GroupModel getObject(String [] record){

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        // temp
        List<StudentModel> students = new ArrayList<StudentModel>();

        return new GroupModel(id, name, students);
    }

    public void saveObject(GroupModel group){

        String group_id = String.valueOf(group.getId());
        String name = group.getName();
        // dodac List<StudentModel>
        String query = String.format("UPDATE %s SET name=%s " +
                "WHERE id=%s;", DEFAULT_TABLE, name, group_id);
        DbManagerDAO dao = new DbManagerDAO();
        dao.inputData(query);
    }

    public void saveObjects(List<GroupModel> groups){

        for(GroupModel group : groups) {
            saveObject(group);
        }
    }
}
