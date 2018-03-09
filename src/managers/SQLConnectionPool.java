package managers;

import enums.FilePath;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SQLConnectionPool {

    private final static int MIN_CONNECTION_NUMBER = 5;
    private String url;
    private final int minConQuantity;
    private ConcurrentLinkedDeque <Connection> pool;
    private static SQLConnectionPool instance;
    private DbFitter dbFitter;

    public static SQLConnectionPool getSqlConnectionPool(int minConQuantity,
                                                         String url,
                                                         DbFitter dbFitter,
                                                         FilePath sqlUpdaterPath) {
        try {
            if (instance == null) {
                instance = new SQLConnectionPool(minConQuantity, url, dbFitter, sqlUpdaterPath);
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private SQLConnectionPool(final int minConQuantity, String url, DbFitter dbFitter, FilePath sqlUpdaterPath)
            throws IllegalArgumentException, SQLException, FileNotFoundException {

        if (minConQuantity < MIN_CONNECTION_NUMBER) {
            throw new IllegalArgumentException();
        }
        this.minConQuantity = minConQuantity;
        this.url = url;
        this.dbFitter = dbFitter;
        setPool();
        dbFitter.updateDatabaseWithSqlFile(sqlUpdaterPath, getConnection());
    }

    private void setPool() throws SQLException {
        this.pool = new ConcurrentLinkedDeque<>();
        for (int i=0; i<minConQuantity; i++) {
            pool.add(createConnection());
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        if((connection = pool.poll()) == null) {
            connection = createConnection();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if(dbFitter.validateConnection(connection)) {
            pool.offer(connection);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
