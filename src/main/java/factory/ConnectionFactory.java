package factory;

import managers.DatabaseConnection;

import java.sql.Connection;

public class ConnectionFactory {

    private static DatabaseConnection databaseConnection;

    public static void setSqlConnectionGetter(DatabaseConnection connectionGetter) {
        databaseConnection = connectionGetter;
    }

    public static Connection getConnection() {
        try {
            return databaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void shutdownConnections() {
        databaseConnection.shutdown();
    }
}
