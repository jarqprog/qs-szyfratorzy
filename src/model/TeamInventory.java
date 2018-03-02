package model;

public class TeamInventory extends Inventory {

    TeamInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = dao.load(ownerId);
    }
}
