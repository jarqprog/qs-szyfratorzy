package dao;

import application.Table;

public class TeamInventoryDAO extends InventoryDAO {

    public TeamInventoryDAO() {
        this.DEFAULT_TABLE = Table.TEAMS_ARTIFACTS.getName();
    }

}
