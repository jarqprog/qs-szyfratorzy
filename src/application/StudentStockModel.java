package application;

import item.ItemModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class StudentStockModel {
    protected int id;
    protected Map<ItemModel, ?> stock;

    public StudentStockModel(int id) {
        this.id = id;
        stock = new HashMap();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(Map stock) {
        this.stock = stock;
    }

    public ItemModel getItem(int itemId) {
        Set<ItemModel> items = stock.keySet();
        ItemModel item = null;
        for (ItemModel element : items) {
            if (itemId == element.getId()) {
                item = element;
                break;
            }
        }
        return item;
    }

    public boolean containsItem(ItemModel item) {
        boolean elementFound = false;
        for (ItemModel element : stock.keySet()) {
            if (element.compare(item)) {
                elementFound = true;
                break;
            }
        }
        return elementFound;
    }


    public abstract Map getStock();

    public abstract void setStock();     //use DAO

    public abstract void addItem();

}
