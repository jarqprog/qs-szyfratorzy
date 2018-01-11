package item;

public class ArtifactDAO extends ItemsDAO
{
    public ArtifactDAO(){
        defaultFileName = "artifacts.csv";
        defaultFilePath = "DataFiles/artifacts.csv";
        prepareFile();

    }

}
