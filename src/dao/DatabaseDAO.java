package dao;

import exceptions.DatabaseFailure;

public interface DatabaseDAO {

    void prepareDatabase();
    void closeConnection();
    void updateDatabase(String sqlScriptPath) throws DatabaseFailure;
}
