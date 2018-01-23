package shop;

import java.time.LocalDate;

public class TransactionModel {
    private LocalDate localDate;
    private int studentId;
    private int itemId;
    private int itemPrice;

    private TransactionModel(int studentId, int itemId, int itemPrice) {
        this.studentId = studentId;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
    }

    public String toString() {
        String output = String.format("Date: %tF, Student: %d, Item: %d, Price: %d", localDate.now(), studentId, itemId, itemPrice);
        return output;
    }
}
