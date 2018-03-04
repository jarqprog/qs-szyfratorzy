package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import dao.DaoFactory;
import dao.StudentsQuestsDAO;

public class StudentsQuests extends StudentStock {

    private Map<Quest,LocalDate> stock;

    StudentsQuests(int ownerId) {
        super(ownerId);
        stock = new HashMap<>();
    }

    public void setStock(Map<Quest,LocalDate> stock) {
        this.stock = stock;
    }

    public void addItem(Quest quest) {
        LocalDate date = LocalDate.now();
        stock.put(quest, date);
        saveObject();
    }

    public Map<Quest,LocalDate> getStock() {
        return stock;
    }

    public void setStock() {
        stock = DaoFactory.getByType(StudentsQuestsDAO.class).load(ownerId);
    }

    public Quest getItem(int itemId) {
        for (Map.Entry<Quest,LocalDate> entry : stock.entrySet()) {
            Quest inStockItem = entry.getKey();
            if (inStockItem.getId() == itemId) {
                return inStockItem;
            }
        }
        return null;
    }

    public boolean containsItem(Quest item) {
        for (Map.Entry<Quest,LocalDate> entry : stock.entrySet()) {
            Quest inStockItem = entry.getKey();
            if (inStockItem.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Quest,LocalDate> entry : stock.entrySet()) {
            Quest quest= entry.getKey();
            String date = entry.getValue().toString();
            stringBuilder.append(String.format("\tId: %d, Quest: %s, Date: %s\n",
                    quest.getId(), quest.getName(), date));
        }
        return stringBuilder.toString();
    }

    public boolean isEmpty () {
        return stock.size() == 0;
    }
}

