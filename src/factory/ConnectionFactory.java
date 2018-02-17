package factory;

import enums.FilePath;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String DATA_BASE_PATH = FilePath.DATA_BASE.getPath();
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:" + DATA_BASE_PATH;
    private static Connection connection;

    public static Connection getConnection() {

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
}
