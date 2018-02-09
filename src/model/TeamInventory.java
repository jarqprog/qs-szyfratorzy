package model;

import dao.TeamInventoryDAO;

public class TeamInventory extends Inventory {

    public TeamInventory(int ownerId) {
        super(ownerId);
        dao = new TeamInventoryDAO();
    }

    public void setStock() {
        stock = dao.load(ownerId);
    }

    protected void saveObject () {
        dao.save(this);
    }
}
