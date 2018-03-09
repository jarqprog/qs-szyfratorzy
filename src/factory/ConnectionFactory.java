package factory;

import managers.SQLConnectionPool;
import enums.DatabaseUrl;
import enums.FilePath;
import managers.DbFitter;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final int MIN_CONNECTION_QUANTITY = 7;
    private static final String DB_URL = DatabaseUrl.SQLITE_URL.getUrl() + FilePath.DATA_BASE.getPath();
    private static final DbFitter DB_FITTER = new DbFitter();
    private static final FilePath SQL_UPDATER_PATH = FilePath.SQL_SCRIPT;

    private static SQLConnectionPool sqlConnectionPool = SQLConnectionPool.getSqlConnectionPool(
                                                                MIN_CONNECTION_QUANTITY, DB_URL,
                                                                DB_FITTER, SQL_UPDATER_PATH);

    public static Connection getConnection() {
        try {
            return sqlConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
