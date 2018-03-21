package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void constructor_createsProperTransactionObject() {
        Transaction transaction = new Transaction(1, 1, 10);
        LocalDate date  = LocalDate.now();
        String expected = String.format("\tDate: %tF, Student: 1, Item: 1, Price: 10",date);
        assertEquals(expected, transaction.toString());
    }

    @Test
    void constructor_transactionPriceIsSetToZeroWhenNegativeNumberPassed() {
        Transaction transaction = new Transaction(1,1, -100);
        int expected = 0;
        assertEquals(expected, transaction.getItemPrice());
    }
}