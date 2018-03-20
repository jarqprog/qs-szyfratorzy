package enums;

public enum DatabaseSetup {

    SQLITE_URL("jdbc:sqlite:data_base/quest_store.db"), SQLITE_DRIVER("org.sqlite.JDBC");

    private String data;

    DatabaseSetup(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }


}
