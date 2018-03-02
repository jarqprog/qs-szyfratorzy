package dao;

import enums.Table;

import java.sql.Connection;

public class TeamInventoryDAO extends InventoryDAO {

    public TeamInventoryDAO(Connection connection) {
        super(connection);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.TEAMS_ARTIFACTS.getName();
    }
}
