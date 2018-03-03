package dao;

import managers.ResultSetManager;

import java.sql.Connection;
import java.util.*;


public class ShopDAOImpl implements ShopDAO {

    private Connection connection;

    ShopDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void saveStudentTransaction(int studentID, int artifactID) {
        ResultSetManager manager = new ResultSetManager();
        Date date = new Date();
        String transactionDate = date.toString();
        // it will be changed
        String query = String.format("INSERT INTO students_transactions " +
                        "VALUES(null, %s, %s, '%s');", studentID, artifactID, transactionDate);
        manager.inputData(query);
    }
}
