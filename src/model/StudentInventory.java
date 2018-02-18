package model;

import dao.StudentInventoryDAO;

public class StudentInventory extends Inventory {


    StudentInventory(int ownerId) {
        super(ownerId);
        dao = new StudentInventoryDAO();
    }

    public void setStock() {
        stock = dao.load(ownerId);
    }
}
