package school;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class GroupDAO {

    private DbManagerDAO dao;
    private final String DEFAULT_TABLE = Table.GROUPS.getName();
    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;


    public List<GroupModel> getManyObjects(List<String[]> dataCollection) {

        List<GroupModel> groups = new ArrayList<GroupModel>();

        for (String [] record : dataCollection) {
            GroupModel group = getOneObject(record);
            groups.add(group);
        }

        return groups;
    }

    public List<GroupModel> getManyObjects(String query) {

        List<GroupModel> groups = new ArrayList<>();
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        for (String [] record : dataCollection) {
            GroupModel group = getOneObject(record);
            groups.add(group);
        }
        return groups;
    }

    public GroupModel getOneObject(String [] record){

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        // temp
        List<StudentModel> students = new ArrayList<StudentModel>();

        return new GroupModel(id, name, students);
    }

    public GroupModel getOneObject(String query){

        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        List<StudentModel> students = new ArrayList<>();  //temp

        return new GroupModel(id, name, students);
    }


    public void saveObject(GroupModel group){

        String group_id = String.valueOf(group.getId());
        String name = group.getName();
        String query;
        // dodac List<StudentModel>
        if(group_id.equals("-1")){
            query = String.format("INSERT INTO %s VALUES(null, '%s');", DEFAULT_TABLE, name);
        } else{
            query = String.format("UPDATE %s SET name='%s' " +
                    "WHERE id=%s;", DEFAULT_TABLE, name, group_id);
        }
        dao = new DbManagerDAO();
        dao.inputData(query);
    }

    public void saveObjects(List<GroupModel> groups){

        for(GroupModel group : groups) {
            saveObject(group);
        }
    }
}
