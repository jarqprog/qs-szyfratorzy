package model;

import dao.StudentSetDAO;
import model.Group;
import enums.Table;

import java.sql.Connection;

public class GroupDAO extends StudentSetDAO<Group> {

    GroupDAO(Connection connection) {
        super(connection);
    }

    public Group extractModel(String [] record){

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        return new Group(id, name);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.GROUPS.getName();
    }
}
