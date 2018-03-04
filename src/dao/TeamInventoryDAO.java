package dao;

import enums.Table;
import model.TeamInventory;

import java.sql.Connection;

public class TeamInventoryDAO extends InventoryDAO<TeamInventory> {

    public TeamInventoryDAO(Connection connection) {
        super(connection);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.TEAMS_ARTIFACTS.getName();
    }
}
