package item;

import java.time.LocalDate;

import application.StudentStockModel;




public class StudentsQuestsModel extends StudentStockModel {

    public StudentsQuestsModel(int ownerId) {
        super(ownerId);
    }

    public void setStock() {

    }

    public void addItem(QuestModel quest) {
      LocalDate date = LocalDate.now();
        super.getMap().put(quest, date);
    }
}

