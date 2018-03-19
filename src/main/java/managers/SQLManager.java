package managers;

import enums.FilePath;

import java.io.FileNotFoundException;
import java.sql.Connection;


public interface SQLManager {

    boolean validateConnection(Connection connection);
    void closeConnection(Connection connection);
    void updateDatabaseWithSqlFile(FilePath sqlSetupScriptPath, Connection connection) throws FileNotFoundException;
}
