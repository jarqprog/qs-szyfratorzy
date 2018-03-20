package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        if (! containsQuest(quest)) {
            LocalDate date = LocalDate.now();
            stock.put(quest, date);
            saveModel();
        }
    }

    public Map<Quest,LocalDate> getStock() {
        return stock;
    }

    public void setStock() {
        stock = ModelDaoFactory.getByType(StudentsQuestsDAO.class).load(getOwnerId());
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

    public boolean containsQuest(Quest quest) {
        for (Map.Entry<Quest,LocalDate> entry : stock.entrySet()) {
            Quest inStockItem = entry.getKey();
            if (inStockItem.getName().equals(quest.getName())) {
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
            stringBuilder.append(String.format("\tId: %d, Quest: %s, Date: %s, Status: %s\n",
                    quest.getId(), quest.getName(), date, quest.getStatus()));
        }
        return stringBuilder.toString();
    }

    public boolean isEmpty () {
        return stock.size() == 0;
    }
}

