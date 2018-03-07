package managers;

import java.util.List;

public interface InFileFinder {

    String getDataByLine(int lineNumber);
    List<String> getFilteredData(String filter);

}
