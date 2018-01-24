package item;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

import application.DbManagerDAO;
import application.Table;

public class ArtifactDAO {

    private final String DEFAULT_TABLE = Table.ARTIFACTS.getName();
    private DbManagerDAO daoManager = new DbManagerDAO();

    private final static int ID_INDEX = 0;
    private final static int TYPE_INDEX = 1;
    private final static int NAME_INDEX = 2;
    private final static int DESCRIPTION_INDEX = 3;
    private final static int PRICE_INDEX = 4;

    public List<ArtifactModel> getObjects(List<String[]> dataCollection) {

        ArrayList<ArtifactModel> inventory = new ArrayList<ArtifactModel>();

        for (String [] record : dataCollection) {
            ArtifactModel artifact = getObject(record);
            inventory.add(artifact);
        }
        return inventory;
    }

    public ArtifactModel getObject(String[] record) {
        int id = Integer.parseInt(record[ID_INDEX]);
        char itemType = record[TYPE_INDEX].charAt(0);
        String itemName = record[NAME_INDEX];
        String itemDescription = record[DESCRIPTION_INDEX];
        int price = Integer.parseInt(record[PRICE_INDEX]);

        return new ArtifactModel(id, itemType, itemName, itemDescription, price);
    }

    public void saveObject(ArtifactModel artifact) {
        String artifact_id = String.valueOf(artifact.getId());
        String itemType = String.valueOf(artifact.getItemType());
        String itemName = artifact.getItemName();
        String itemDescription = artifact.getItemDescription();
        String price = String.valueOf(artifact.getPrice());

        String query = String.format("UPDATE %s SET name=%s , type=%s, description=%s, price=%s " +
                "WHERE artifact_id=%s;", DEFAULT_TABLE, itemName, itemType, itemDescription, price, artifact_id);

        daoManager.inputData(query);
    }

    public void saveObjects(List<ArtifactModel> artifacts) {

        for(ArtifactModel artifact : artifacts) {
            saveObject(artifact);
        }
    }

}
