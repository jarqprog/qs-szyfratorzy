package model;

import dao.DaoFactory;
import dao.TeamInventoryDAO;

public class TeamInventory extends Inventory {

    TeamInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = DaoFactory.getByType(TeamInventoryDAO.class).load(ownerId);
    }
}
