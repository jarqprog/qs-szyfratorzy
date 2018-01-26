package item;

import java.util.List;
import java.util.ArrayList;

import application.DbManagerDAO;
import application.FactoryDAO;
import application.Table;

public class ArtifactDAO extends FactoryDAO {

    private final static int ID_INDEX = 0;
    private final static int TYPE_INDEX = 2;
    private final static int NAME_INDEX = 1;
    private final static int DESCRIPTION_INDEX = 3;
    private final static int PRICE_INDEX = 4;

    public ArtifactDAO(){
        this.DEFAULT_TABLE = Table.ARTIFACTS.getName();
    }

    public List<ArtifactModel> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public List<ArtifactModel> getManyObjects(List<String[]> dataCollection) {
        ArrayList<ArtifactModel> artifacts = new ArrayList<>();

        for (String [] record : dataCollection) {
            ArtifactModel artifact = getOneObject(record);
            artifacts.add(artifact);
        }
        return artifacts;
    }

    public ArtifactModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }

    public ArtifactModel getOneObject(String[] record) {
        int id = Integer.parseInt(record[ID_INDEX]);
        char itemType = record[TYPE_INDEX].charAt(0);
        String itemName = record[NAME_INDEX];
        String itemDescription = record[DESCRIPTION_INDEX];
        int price = Integer.parseInt(record[PRICE_INDEX]);

        return new ArtifactModel(id, itemType, itemName, itemDescription, price);
    }

    public void saveObject(ArtifactModel artifact) {
        String artifactId = String.valueOf(artifact.getId());
        String itemType = String.valueOf(artifact.getType());
        String itemName = artifact.getName();
        String itemDescription = artifact.getDescription();
        String price = String.valueOf(artifact.getPrice());

        String query;
        if (artifactId.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, itemType, itemName, itemDescription, price);
        } else {
            query = String.format("UPDATE %s SET name='%s' , type='%s', description='%s', price=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, itemName, itemType, itemDescription, price, artifactId);
        }
        DbManagerDAO dao = new DbManagerDAO();
        dao.inputData(query);
    }

    public void saveObjects(List<ArtifactModel> artifacts) {

        for(ArtifactModel artifact : artifacts) {
            saveObject(artifact);
        }
    }

}
