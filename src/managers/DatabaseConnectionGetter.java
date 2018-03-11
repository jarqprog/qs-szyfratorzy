package managers;

import java.sql.Connection;

public interface SQLConnectionGetter {

    Connection getConnection() throws Exception;
    void shutdown();
}
