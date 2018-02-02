package school;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;

public class GroupDAO extends FactoryDAO {

    public GroupDAO(){
        this.DEFAULT_TABLE = Table.GROUPS.getName();
    }

    public GroupModel getOneObject(String [] record){

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        return new GroupModel(id, name);
    }

    public <T> void saveObject(T t){
        GroupModel group = (GroupModel) t;
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
}
