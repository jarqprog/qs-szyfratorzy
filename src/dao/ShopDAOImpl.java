package dao;

import java.sql.Connection;

public class ShopDAOImpl implements ShopDAO {

    private Connection connection;

    ShopDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void saveStudentTransaction(int studentID, int artifactID) {
//
//        Date date = new Date();
//        String transactionDate = date.toString();
//
//        String query = String.format("INSERT INTO students_transactions " +
//                        "VALUES(null, %s, %s, '%s');", studentID, artifactID, transactionDate);

    }
}
