package model;

import dao.StudentInventoryDAO;

public class StudentInventory extends Inventory {


    public StudentInventory(int ownerId) {
        super(ownerId);
        dao = new StudentInventoryDAO();
    }

    public void setStock() {
        stock = dao.load(ownerId);
    }

    protected void saveObject () {
        dao.save(this);
    }

}
