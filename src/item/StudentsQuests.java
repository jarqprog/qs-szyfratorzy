package item;

import application.StudentStockModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class StudentsQuests extends StudentStockModel {

    public StudentsQuests(int studentId) {
        super(studentId);

//        LocalDate now = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
//        String formatDateTime = now.format(formatter);
    }

    public Map<QuestModel,LocalDate> getStock() {

        return new HashMap<>();
    }


    public void setStock() {

    }


//    public void addItem(QuestModel quest) {
//
//        super.getMapa().put(quest, );
//
//    }


}
