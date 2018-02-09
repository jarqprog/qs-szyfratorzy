package model;

import dao.InventoryDAO;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public abstract class Inventory extends StudentStock {

    protected Map<Artifact,Integer> stock;
    protected InventoryDAO dao;

    public Inventory(int ownerId) {
        super(ownerId);
        stock = new HashMap<>();
    }

    public Map<Artifact,Integer> getStock() {
        return stock;
    }

    public void addItem(Artifact item) {
        stock.put(item, 1);
        saveObject();
    }

    public void removeArtifact(Artifact artifact) {
        Artifact inStockItem = getItem(artifact.getId());
        stock.remove(inStockItem);
        saveObject();
    }

    public void modifyQuantity(Artifact artifact) {
        Artifact inStockItem = getItem(artifact.getId());
        Integer value = stock.get(inStockItem);
        stock.put(inStockItem, value + 1);
        saveObject();
    }

    public void decreaseQuantity(Artifact artifact) {
        Artifact inStockItem = getItem(artifact.getId());
        Integer value = stock.get(inStockItem);
        stock.put(inStockItem, value - 1);
        saveObject();
    }

    public Artifact getItem(int itemId) {
        Set<Artifact> inventory = stock.keySet();
        for(Artifact inStockItem: inventory){
            if (inStockItem.getId() == itemId) {
                return inStockItem;
            }
        }
    return null;
    }

    public boolean containsItem(Artifact item) {
        return getItem(item.getId()) != null;
    }

    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Artifact,Integer> entry : stock.entrySet()) {
            Artifact artifact = entry.getKey();
            Integer quantity = entry.getValue();
            stringBuilder.append(String.format("Id: %d, Artifact: %s, Quantity: %d\n",
                    artifact.getId(), artifact.getName(), quantity));
        }
        return stringBuilder.toString();
    }

    public boolean isEmpty () {
        return stock.size() == 0;
    }

    protected abstract void saveObject();

    public abstract void setStock();

}