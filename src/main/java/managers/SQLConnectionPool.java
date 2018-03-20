package managers;

import enums.FilePath;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SQLConnectionPool implements DatabaseConnection {

    private final static int MIN_ALLOWED_CONNECTION_NUMBER = 3;
    private final static int MAX_ALLOWED_CONNECTION_NUMBER = 10;
    private String url;
    private String password;  // not used by sqlite
    private String userName;  // not used by sqlite
    private String driver;
    private Integer minConQuantity;
    private Integer maxConQuantity;
    private ConcurrentLinkedDeque <Connection> pool;
    private static SQLConnectionPool instance;
    private SQLManager sqlManager;

    public static SQLConnectionPool getSqliteConnectionPool(DatabaseConfig config, SQLManager sqlManager, FilePath sqlUpdateScriptFile) {
        try {
            if (instance == null) {
                instance = new SQLConnectionPool(config, sqlManager, sqlUpdateScriptFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return instance;
    }

    private SQLConnectionPool(DatabaseConfig sqlConfig, SQLManager sqlManager, FilePath sqlUpdateScriptFile)
            throws ClassNotFoundException, SQLException, FileNotFoundException {
        this.minConQuantity = sqlConfig.getMIN_CONNECTIONS();
        if (minConQuantity < MIN_ALLOWED_CONNECTION_NUMBER) {
            throw new IllegalArgumentException("too small minimum number of connections in the configuration class");
        }
        this.maxConQuantity = sqlConfig.getMAX_CONNECTIONS();
        if (maxConQuantity > MAX_ALLOWED_CONNECTION_NUMBER) {
            throw new IllegalArgumentException("too big maximum number of connections in the configuration class");
        }
        this.password = sqlConfig.getPASSWORD();
        this.userName = sqlConfig.getUSER_NAME();
        this.driver = sqlConfig.getDRIVER();
        this.url = sqlConfig.getURL();
        this.sqlManager = sqlManager;
        setPool();
        sqlManager.updateDatabaseWithSqlFile(sqlUpdateScriptFile, getConnection());
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
        if((connection = pool.poll()) == null) {
            connection = createConnection();
        }
        return connection;
    }

    public void shutdown() {
        for (Connection connection : pool) {
            sqlManager.closeConnection(connection);
        }
    }

    public void returnConnection(Connection connection) {
        if(sqlManager.validateConnection(connection)) {
            pool.offer(connection);
        }
    }

    private void setPool() throws ClassNotFoundException, SQLException {
        this.pool = new ConcurrentLinkedDeque<>();
        for (int i=0; i<minConQuantity; i++) {
            pool.add(createConnection());
        }
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }
}
