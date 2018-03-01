package managers;

import exceptions.DatabaseFailure;

public interface DbManager {

    void prepareDatabase();
    void closeConnection();
    void updateDatabase(String sqlScriptPath) throws DatabaseFailure;
}
