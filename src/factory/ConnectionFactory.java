package factory;

import managers.DatabaseConnectionGetter;

import java.sql.Connection;

public class ConnectionFactory {

    private static DatabaseConnectionGetter databaseConnectionGetter;

    public static void setSqlConnectionGetter(DatabaseConnectionGetter connectionGetter) {
        databaseConnectionGetter = connectionGetter;
    }

    public static Connection getConnection() {
        try {
            return databaseConnectionGetter.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void shutdownConnections() {
        databaseConnectionGetter.shutdown();
    }
}
