package enums;

public enum DatabaseUrl {

    SQLITE_URL("jdbc:sqlite:");

    private String url;

    DatabaseUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
}
