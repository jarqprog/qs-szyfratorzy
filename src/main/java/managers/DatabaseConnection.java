package managers;

import java.sql.Connection;

public interface DatabaseConnection {

    Connection getConnection() throws Exception;
    void shutdown();
}
