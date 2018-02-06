package shop;

import item.ArtifactModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InventoryModel {

    private int id;
    private Map<ArtifactModel, Integer> inventory;

    public InventoryModel(int id) {

        this.id = id;
        inventory = new HashMap<>();
        }


    public Map<ArtifactModel, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<ArtifactModel, Integer> inventory) {
        this.inventory = inventory;
    }
    
}






