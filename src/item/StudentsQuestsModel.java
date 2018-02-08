package item;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import application.StudentStockModel;
import shop.InventoryDAO;
import shop.StudentInventoryDAO;


public class StudentsQuestsModel extends StudentStockModel {

    Map<QuestModel,LocalDate> stock;

    public StudentsQuestsModel(int ownerId) {
        super(ownerId);
        stock = new HashMap<>();
    }

    public void setStock(Map<QuestModel,LocalDate> stock) {
        this.stock = stock;
    }

    public void addItem(QuestModel quest) {
        LocalDate date = LocalDate.now();
        stock.put(quest, date);
    }

    public Map<QuestModel,LocalDate> getStock() {
        setStock();
        return stock;
    }

    public void setStock() {
        StudentsQuestsDAO dao = new StudentsQuestsDAO();
        stock = dao.loadQuests(ownerId);
    }

    public QuestModel getItem(int itemId) {
        for (Map.Entry<QuestModel,LocalDate> entry : stock.entrySet()) {
            QuestModel inStockItem = entry.getKey();
            if (inStockItem.getId() == itemId) {
                return inStockItem;

            }
        }
        return null;
    }

    public boolean containsItem(QuestModel item) {
        for (Map.Entry<QuestModel,LocalDate> entry : stock.entrySet()) {
            QuestModel inStockItem = entry.getKey();
            if (inStockItem.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<QuestModel,LocalDate> entry : stock.entrySet()) {
            QuestModel quest= entry.getKey();
            String date = entry.getValue().toString();
            stringBuilder.append(String.format("Id: %d, Quest: %s, Date: %s\n",
                    quest.getId(), quest.getName(), date));
        }
        return stringBuilder.toString();
    }

    public void saveObject () {
        StudentsQuestsDAO dao = new StudentsQuestsDAO();
        dao.saveQuests(this);
    }

    public boolean isEmpty () {
        return stock.size() > 0;
    }
}

