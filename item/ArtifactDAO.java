package item;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

public class ArtifactDAO extends ItemsDAO
{
    public ArtifactDAO(){
        defaultFileName = "artifacts.csv";
        defaultFilePath = "DataFiles/artifacts.csv";
        prepareFile();
    }

    // public List<ArtifactModel> getArtefactsFromFile(){
    //     updateLoadedTables();
    //
    //
    // }




}
