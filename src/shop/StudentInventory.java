package shop;

import java.util.HashMap;

public class StudentInventory extends InventoryModel{

    public StudentInventory(int id) {
        super(id);
        this.inventory = new HashMap<>();

    }

    @Override
    public void updateInventory() {
        ShopDAO shopDAO = new ShopDAO();
        shopDAO.loadStudentInventory(id);
    }

}
