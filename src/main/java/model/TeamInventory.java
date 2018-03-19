package model;

public class TeamInventory extends Inventory {

    TeamInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        setStock(ModelDaoFactory.getByType(TeamInventoryDAO.class).load(getOwnerId()));
    }
}
