package managers;

import java.sql.Connection;

public interface DbConnectionGetter {

    Connection getConnection();
}
