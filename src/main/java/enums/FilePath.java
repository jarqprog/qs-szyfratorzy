package enums;

public enum FilePath {


    SQL_SCRIPT("data_base/init.sql"), SQLITE_DATABASE("data_base/quest_store.db"),
    INTRO("data_files/intro.txt"), OUTRO("data_files/outro.txt"),
    UPDATE_EXP_LVL("data_base/defaultExpLvl.sql"), AUTHORS("data_files/about.md");

    private String filePath;

    FilePath(String filePath){
        this.filePath = filePath;
    }

    public String getPath(){
        return filePath;
    }
}
