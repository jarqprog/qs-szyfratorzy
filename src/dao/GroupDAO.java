package dao;

import managers.TemporaryManager;
import model.Group;
import enums.Table;

import java.sql.Connection;

public class GroupDAO extends ActiveModelDAOImpl<Group> {

    public GroupDAO(Connection connection) {
        super(connection);
    }

    public Group getOneObject(String [] record){

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        return new Group(id, name);
    }

    public void save(Group group){
        String group_id = String.valueOf(group.getId());
        String name = group.getName();
        String query;

        if(group_id.equals("-1")){
            query = String.format("INSERT INTO %s VALUES(null, '%s');", DEFAULT_TABLE, name);
        } else{
            query = String.format("UPDATE %s SET name='%s' " +
                    "WHERE id=%s;", DEFAULT_TABLE, name, group_id);
        }

        dao = new TemporaryManager();
        dao.inputData(query);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.GROUPS.getName();
    }
}
