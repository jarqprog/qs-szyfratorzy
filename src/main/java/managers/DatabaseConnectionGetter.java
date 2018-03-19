package managers;

import java.sql.Connection;

public interface DatabaseConnectionGetter {

    Connection getConnection() throws Exception;
    void shutdown();
}
