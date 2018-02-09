package dao;

import enums.Table;

import java.util.*;


public class ShopDAO {

    private static final int INDEX_ART_ID = 0;
    private static final int INDEX_STUDENTS_ARTIFACTS_ID = 1;
    protected DbManagerDAO dao;
    protected ArtifactDAO artifactDAO;
    protected String STUDENTS_ARTIFACTS_TABLE;
    protected String TEAMS_ARTIFACTS_TABLE;
    private Date date;

    public ShopDAO(){ this.STUDENTS_ARTIFACTS_TABLE = Table.STUDENTS_ARTIFACTS.getName();
                    this.TEAMS_ARTIFACTS_TABLE = Table.TEAMS_ARTIFACTS.getName();
    }

    public void saveStudentTransaction(int studentID, int artifactID) {
        dao = new DbManagerDAO();
        date = new Date();
        String transactionDate = date.toString();
        String query = String.format("INSERT INTO students_transactions " +
                        "VALUES(null, %s, %s, '%s');", studentID, artifactID, transactionDate);
        dao.inputData(query);
    }
}
