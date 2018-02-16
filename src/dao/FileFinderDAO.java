package dao;

import java.util.List;

public interface FileFinderDAO {

    String getDataByLine(int lineNumber);
    List<String> getFilteredData(String filter);

}
