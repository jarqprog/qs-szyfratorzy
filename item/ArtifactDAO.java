package item;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

public class ArtifactDAO extends ItemsDAO{

    protected final static int GENRE_INDEX = 0;
    protected final static int ID_INDEX = 1;
    protected final static int TYPE_INDEX = 2;
    protected final static int NAME_INDEX = 3;
    protected final static int DESCRIPTION_INDEX = 4;
    protected final static int PRICE_INDEX = 5;

    public ArtifactDAO(){
        defaultFileName = "artifacts.csv";
        defaultFilePath = "DataFiles/artifacts.csv";
        prepareFile();
    }

    public List<ArtifactModel> getArtifactsFromFile(){
        List<String[]> filteredData = new ArrayList<String[]>();
        List<ArtifactModel> artifacts = new ArrayList<ArtifactModel>();
        updateLoadedTables();
        for(String[] table : loadedTables){
            if(checkIfDataMatches("artifact", GENRE_INDEX, table)){
                artifacts.add(createArtifactFromData(table));
            }
        }
        return artifacts;
    }

    public ArtifactModel createArtifactFromData(String[] table){
        int id = Integer.parseInt(table[ID_INDEX]);
        char type = table[TYPE_INDEX].charAt(0);
        String name = table[NAME_INDEX];
        String description = table[DESCRIPTION_INDEX];
        int price = Integer.parseInt(table[PRICE_INDEX]);

        return new ArtifactModel(id, type, name, description, price);
    }

    




}
