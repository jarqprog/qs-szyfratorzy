package managers;

import enums.FilePath;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileNotFoundException;
import java.io.File;

public class DbManagerImpl extends AbstractManager implements DbManager {

    private final static String SQL_SCRIPT_PATH = FilePath.SQL_SCRIPT.getPath();
    private static final String DATA_BASE_PATH = FilePath.DATA_BASE.getPath();
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:" + DATA_BASE_PATH;

    protected Connection connection;

    public DbManagerImpl() {
        prepareFile(DATA_BASE_PATH);
    }

    public Connection getConnection() {

        if(connection == null) {
            try {
                Class.forName(CLASS_NAME);
                connection = DriverManager.getConnection(URL);
                return connection;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                connection = null;
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void prepareDatabase() {
        try {
            if (!isConnected()) {
                connection = getConnection();
            }
            updateDatabase();
        } catch (Exception e){
            if (! isConnected()) {
                closeConnection();
            }
        }
    }

    private void updateDatabase(){
        if(connection == null){
            openConnection();
        }
        File sqlFile = new File(SQL_SCRIPT_PATH);
        executeSqlScript(sqlFile);
    }

    public void updateDatabase(String sqlScriptPath){
        if(connection == null){
            openConnection();
        }
        File sqlFile = new File(sqlScriptPath);
        executeSqlScript(sqlFile);
    }

    protected void openConnection() {
        connection = getConnection();
    }

    private boolean isConnected() {
        return connection != null;
    }

    private void executeSqlScript(File inputFile){
        String delimiter = ";";
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
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
