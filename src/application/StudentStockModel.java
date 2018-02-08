package application;

import item.ItemModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;


public abstract class StudentStockModel {
    protected int id;
    protected Map<? extends ItemModel, ?> stock;

    public StudentStockModel(int id) {
        this.id = id;
        stock = new HashMap<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public <K extends ItemModel, V> void setStock(Map<K,V> stock) {
        this.stock = stock;
    }

    public <T extends ItemModel> T getItem(int itemId) {
        for (Map.Entry<?,?> entry : stock.entrySet()) {
            @SuppressWarnings("unchecked")
            T inStockItem = (T) entry.getKey();
            if (inStockItem.getId() == itemId) {
                return inStockItem;
            }
        }
        return null;
    }

    public <T extends ItemModel> boolean containsItem(T item) {
        for (Map.Entry<?,?> entry : stock.entrySet()) {
            @SuppressWarnings("unchecked")
            T inStockItem = (T) entry.getKey();
            if (inStockItem.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public abstract Map getStock();

    public abstract void setStock();     //use DAO

    public abstract void addItem();

}
