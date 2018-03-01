package dao;

import enums.Table;
import managers.TemporaryManager;
import model.Artifact;

public class ArtifactDAO extends ActiveModelDAOImpl<Artifact> {

    public ArtifactDAO(){
        this.DEFAULT_TABLE = Table.ARTIFACTS.getName();
    }

    public Artifact getOneObject(String[] record) {

        final int ID_INDEX = 0;
        final int NAME_INDEX = 1;
        final int TYPE_INDEX = 2;
        final int DESCRIPTION_INDEX = 3;
        final int PRICE_INDEX = 4;

        int id = Integer.parseInt(record[ID_INDEX]);
        char itemType = record[TYPE_INDEX].charAt(0);
        String itemName = record[NAME_INDEX];
        String itemDescription = record[DESCRIPTION_INDEX];
        int price = Integer.parseInt(record[PRICE_INDEX]);

        return new Artifact(id, itemType, itemName, itemDescription, price);
    }

    public void save(Artifact artifact){
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
                    DEFAULT_TABLE, itemName, itemType, itemDescription, price);
        } else {
            query = String.format("UPDATE %s SET name='%s' , type='%s', description='%s', price=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, itemName, itemType, itemDescription, price, artifactId);
        }
        TemporaryManager dao = new TemporaryManager();
        dao.inputData(query);
    }
}
