package model;

import dao.InventoryDAO;
import enums.Table;

import java.sql.Connection;

public class TeamInventoryDAO extends InventoryDAO<TeamInventory> {

    TeamInventoryDAO(Connection connection) {
        super(connection);
    }

    protected void setDefaultTable(){
        setDefaultTable(Table.TEAMS_ARTIFACTS);
    }
}
