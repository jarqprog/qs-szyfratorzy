package managers;

public class DatabaseConfig {

    private final String USER_NAME;
    private final String PASSWORD;
    private final String DRIVER;
    private final String URL;
    private final Integer MIN_CONNECTIONS;
    private final Integer MAX_CONNECTIONS;

    // get configuration object via static method - different for varied database engines
    public static DatabaseConfig createSQLiteConfiguration(String URL, String DRIVER,
                                                           Integer MIN_CONNECTIONS, Integer MAX_CONNECTIONS) {
        return new DatabaseConfig(null, null, URL, DRIVER, MIN_CONNECTIONS, MAX_CONNECTIONS);
    }

    private DatabaseConfig(String USER_NAME, String PASSWORD, String URL,
                           String DRIVER, Integer MIN_CONNECTIONS,
                           Integer MAX_CONNECTIONS) {
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.URL = URL;
        this.DRIVER = DRIVER;
        this.MIN_CONNECTIONS = MIN_CONNECTIONS;
        this.MAX_CONNECTIONS = MAX_CONNECTIONS;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getURL() {
        return URL;
    }

    public String getDRIVER() {
        return DRIVER;
    }

    public Integer getMIN_CONNECTIONS() {
        return MIN_CONNECTIONS;
    }

    public Integer getMAX_CONNECTIONS() {
        return MAX_CONNECTIONS;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "USER_NAME='" + USER_NAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", DRIVER='" + DRIVER + '\'' +
                ", URL='" + URL + '\'' +
                ", MIN_CONNECTIONS=" + MIN_CONNECTIONS +
                ", MAX_CONNECTIONS=" + MAX_CONNECTIONS +
                '}';
    }
}
