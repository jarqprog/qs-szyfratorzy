package model;

public class StudentInventory extends Inventory {


    StudentInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        setStock(ModelDaoFactory.getByType(StudentInventoryDAO.class).load(getOwnerId()));
    }
}
