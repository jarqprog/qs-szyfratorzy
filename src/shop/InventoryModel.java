package shop;

import application.StudentStockModel;
import item.ArtifactModel;
import java.util.Map;

public class InventoryModel extends StudentStockModel {

    public InventoryModel(int id) {
        super(id);
    }

    @Override
    public Map getStock() {
        return stock;
    }

    @Override
    public void setStock() {
        ShopDAO shopDao = new ShopDAO();
        stock = shopDao.loadStudentInventory(id);
    }

    public void addItem(ArtifactModel item) {
        stock.put(item, 1);
    }

    public void removeArtifact(ArtifactModel artifact) {
        stock.remove(artifact);
    }

    public void modifyQuantity(ArtifactModel artifact) {
        Integer value = (Integer) stock.get(artifact);
        stock.put(artifact, ++value);
    }

    public void decreaseQuantity(ArtifactModel artifact) {
        Integer value = (Integer) stock.get(artifact);
        stock.put(artifact, --value);
    }

    public String toString() {
        String inventory = " ";
        for (Map.Entry<ArtifactModel, Integer> entry : stock.entrySet()){
            ArtifactModel artifact = entry.getKey();
            Integer quantity = entry.getValue();
            inventory += String.format("Artifact: %s, Quantity: %d\n", artifact.getName(), quantity);
        }
        return inventory;
    }

}