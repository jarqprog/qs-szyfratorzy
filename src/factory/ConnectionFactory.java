package factory;

import managers.DbConnectionGetter;
import managers.DbManagerImpl;

import java.sql.Connection;

public class ConnectionFactory {

    private static DbConnectionGetter manager = new DbManagerImpl();

    public static Connection getConnection() {
        return manager.getConnection();
    }
}
