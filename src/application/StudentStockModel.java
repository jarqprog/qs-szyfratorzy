package application;


import item.ItemModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;



public abstract class StudentStockModel {

    protected int studentId;
    protected Map<? extends ItemModel, ?> stock;

    public StudentStockModel(int studentId) {
        this.studentId = studentId;
        stock = new HashMap<>();
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
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

    public boolean isEmpty(){
        return stock.size() > 0;
    }

    //public abstract Map getStock();

    //public abstract void setStock();
  
}
