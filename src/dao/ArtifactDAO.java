package dao;

import enums.Table;
import managers.ResultSetManager;
import model.Artifact;

import java.sql.Connection;

public class ArtifactDAO extends ActiveModelDAOImpl<Artifact> {

    ArtifactDAO(Connection connection) {
        super(connection);
    }


    public Artifact extractModel(String[] record) {

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
        ResultSetManager dao = new ResultSetManager();
        dao.inputData(query);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.ARTIFACTS.getName();
    }
}
