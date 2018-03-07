package managers;

import java.util.List;

public interface FileManager {

    void insertData(List<String> data);
    List<String> getData();
    void clearFile();

}
