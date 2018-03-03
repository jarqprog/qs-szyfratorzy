package model;

import java.sql.SQLException;

public class TeamInventory extends Inventory {

    TeamInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        try {
            stock = dao.load(ownerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
