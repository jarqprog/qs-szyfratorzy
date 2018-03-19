package model;

public class TeamInventory extends Inventory {

    TeamInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = ModelDaoFactory.getByType(TeamInventoryDAO.class).load(ownerId);
    }
}
