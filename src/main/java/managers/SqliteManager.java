package managers;

import enums.FilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqliteManager extends AbstractManager implements SQLManager{

    public static SqliteManager getManager(FilePath databaseFilePath) {
        return new SqliteManager(databaseFilePath);
    }

    private SqliteManager(FilePath databaseFilePath) {
        prepareFile(databaseFilePath.getPath());
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateConnection(Connection connection) {
        try {
            return (!(connection == null)) && (!(connection).isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateDatabaseWithSqlFile(FilePath sqlSetupScriptPath, Connection connection) throws FileNotFoundException {
        String delimiter = ";";
        Scanner scanner;
        File sqlFile = new File(sqlSetupScriptPath.getPath());
        scanner = new Scanner(sqlFile).useDelimiter(delimiter);
        Statement currentStatement = null;
        while(scanner.hasNext()) {
            String rawStatement = scanner.next() + delimiter;
            try {
                currentStatement = connection.createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
        scanner.close();
    }
}
