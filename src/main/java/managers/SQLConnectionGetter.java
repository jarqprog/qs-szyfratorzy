package managers;

import enums.FilePath;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionGetter implements DatabaseConnectionGetter {

    private String url;
    private String password;  // not used by sqlite
    private String userName;  // not used by sqlite
    private String driver;
    private SQLManager sqlManager;
    private static Connection connection;

    public static SQLConnectionGetter getSqliteConGetter(DatabaseConfiguration config, SQLManager sqlManager, FilePath sqlUpdateScriptFile) {
        SQLConnectionGetter instance = null;
        try {
            instance = new SQLConnectionGetter(config, sqlManager, sqlUpdateScriptFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return instance;
    }

    public void shutdown() {
        sqlManager.closeConnection(connection);
    }

    private SQLConnectionGetter(DatabaseConfiguration sqlConfig, SQLManager sqlManager, FilePath sqlUpdateScriptFile)
            throws ClassNotFoundException, SQLException, FileNotFoundException {
        this.password = sqlConfig.getPASSWORD();
        this.userName = sqlConfig.getUSER_NAME();
        this.driver = sqlConfig.getDRIVER();
        this.url = sqlConfig.getURL();
        this.sqlManager = sqlManager;
        sqlManager.updateDatabaseWithSqlFile(sqlUpdateScriptFile, getConnection());
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }
}
