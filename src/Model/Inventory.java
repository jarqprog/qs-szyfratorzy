package Model;

import dao.InventoryDAO;
import application.StudentStockModel;
import item.ArtifactModel;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public abstract class Inventory extends StudentStockModel {

    protected Map<ArtifactModel,Integer> stock;
    protected InventoryDAO dao;

    public Inventory(int ownerId) {
        super(ownerId);
        stock = new HashMap<>();
    }

    public Map<ArtifactModel,Integer> getStock() {
        return stock;
    }

    public void addItem(ArtifactModel item) {
        stock.put(item, 1);
        saveObject();
    }

    public void removeArtifact(ArtifactModel artifact) {
        stock.remove(artifact);
        saveObject();
    }

    public void modifyQuantity(ArtifactModel artifact) {
        Set<ArtifactModel> inventory = stock.keySet();

        for(ArtifactModel inStockItem: inventory){
            if(inStockItem.getId() == artifact.getId()){
                Integer value = stock.get(inStockItem);
                stock.put(inStockItem, ++value);
            }
        }
        saveObject();
    }

    public void decreaseQuantity(ArtifactModel artifact) {
        Set<ArtifactModel> inventory = stock.keySet();
        for(ArtifactModel inStockItem: inventory){
            if(inStockItem.getId() == artifact.getId()){
                Integer value = stock.get(inStockItem);
                stock.put(artifact, --value);
            }
        }
        saveObject();
    }

    public ArtifactModel getItem(int itemId) {
        if (! isEmpty()) {
            for (Map.Entry<ArtifactModel, Integer> entry : stock.entrySet()) {
                ArtifactModel inStockItem = entry.getKey();
                if (inStockItem.getId() == itemId) {
                    return inStockItem;

                }
            }
        }
        return null;
    }

    public boolean containsItem(ArtifactModel item) {
        for (Map.Entry<ArtifactModel, Integer> entry : stock.entrySet()) {
            ArtifactModel inStockItem = entry.getKey();
            if (inStockItem.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<ArtifactModel,Integer> entry : stock.entrySet()) {
            ArtifactModel artifact = entry.getKey();
            Integer quantity = entry.getValue();
            stringBuilder.append(String.format("Id: %d, Artifact: %s, Quantity: %d\n",
                    artifact.getId(), artifact.getName(), quantity));
        }
        return stringBuilder.toString();
    }

    public boolean isEmpty () {
        return stock.size() == 0;
    }

    protected abstract void saveObject ();

    public abstract void setStock();

}