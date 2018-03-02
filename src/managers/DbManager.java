package managers;

import exceptions.DatabaseFailure;

import java.sql.Connection;

public interface DbManager {

    void prepareDatabase();
    Connection getConnection();
    void closeConnection();
    void updateDatabase(String sqlScriptPath) throws DatabaseFailure;
}
