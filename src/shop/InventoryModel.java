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
        super.getMap().put(item, 1);
    }

    public void removeArtifact(ArtifactModel artifact) {
        stock.remove(artifact);
    }

    public void modifyQuantity(ArtifactModel artifact) {
        Integer value = (Integer) stock.get(artifact);
        super.getMap().put(artifact, ++value);
    }

    public void decreaseQuantity(ArtifactModel artifact) {
        Integer value = (Integer) super.getMap().get(artifact);
        super.getMap().put(artifact, --value);
    }

    public String toString() {
        String inventory = "";
        for (Map.Entry<?,?> entry : super.getMap().entrySet()){
            @SuppressWarnings("unchecked")
            ArtifactModel artifact = (ArtifactModel) entry.getKey();
            @SuppressWarnings("unchecked")
            Integer quantity = (Integer) entry.getValue();
            inventory += String.format("Id: %d, Artifact: %s, Quantity: %d\n",artifact.getId(),  artifact.getName(), quantity);
        }
        return inventory;
    }

}