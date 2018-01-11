package users;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public interface LogableDAO{

    public void updateLoadedTables();
    public Boolean checkIfFileExist();
    public void saveData(List<String> collectionToSave);
    public List<String[]> getLoadedTables();
    public List<String> getLoadedStrings();
    public void setLoadedTables(List<String[]> tablesCollection);
    public void setLoadedStrings(List<String> stringsCollection);
    public AdminModel createFirstAdmin();
    public void createDefaultFile();
    public void prepareFile();

}
