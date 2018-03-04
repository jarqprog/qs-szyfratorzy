package model;

public class StudentInventory extends Inventory {


    StudentInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = dao.load(ownerId);
    }
}
