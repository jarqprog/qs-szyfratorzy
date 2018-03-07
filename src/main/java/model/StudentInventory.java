package model;

public class StudentInventory extends Inventory {


    StudentInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = ModelDaoFactory.getByType(StudentInventoryDAO.class).load(ownerId);
    }
}
