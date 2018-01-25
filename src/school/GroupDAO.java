package school;

import java.util.ArrayList;
import java.util.List;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class GroupDAO extends FactoryDAO {

    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;

    public GroupDAO(){
        this.DEFAULT_TABLE = Table.GROUPS.getName();
    }


    public List<GroupModel> getManyObjects(List<String[]> dataCollection) {
        List<GroupModel> groups = new ArrayList<>();
        for (String [] record : dataCollection) {
            GroupModel group = getOneObject(record);
            groups.add(group);
        }
        return groups;
    }

    public List<GroupModel> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public GroupModel getOneObject(String [] record){
        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        List<StudentModel> students = new ArrayList<>();
        return new GroupModel(id, name, students);
    }

    public GroupModel getOneObject(String query){
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }


    public void saveObject(GroupModel group){
        String group_id = String.valueOf(group.getId());
        String name = group.getName();
        String query;

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
