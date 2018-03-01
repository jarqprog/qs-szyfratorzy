package dao;

import java.util.List;

public interface FileDAO {

    void insertData(List<String> data);
    List<String> getData();
    void clearFile();

}
