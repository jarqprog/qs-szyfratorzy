package model;

import dao.DaoFactory;
import dao.StudentInventoryDAO;

public class StudentInventory extends Inventory {


    StudentInventory(int ownerId) {
        super(ownerId);
    }

    public void setStock() {
        stock = DaoFactory.getByType(StudentInventoryDAO.class).load(ownerId);
    }
}
