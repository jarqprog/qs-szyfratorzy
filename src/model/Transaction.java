package model;

import java.time.LocalDate;

public class Transaction {
    private LocalDate localDate;
    private int studentId;
    private int itemId;
    private int itemPrice;

    public Transaction(int studentId, int itemId, int itemPrice) {
        this.studentId = studentId;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
    }

    public String toString() {
        String output = String.format("\tDate: %tF, Student: %d, Item: %d, Price: %d", localDate.now(), studentId, itemId, itemPrice);
        return output;
    }
}
