package model;

import dao.ActiveModelDAOImpl;
import enums.Table;
import managers.SQLProcessManager;

import java.sql.Connection;
import java.sql.SQLException;

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

    public boolean saveModel(Artifact artifact){
        String artifactId = String.valueOf(artifact.getId());
        String itemType = String.valueOf(artifact.getType());
        String itemName = artifact.getName();
        String itemDescription = artifact.getDescription();
        int price = artifact.getPrice();
        String query;

        if (artifactId.equals("-1")) {

            query = String.format(
                    "INSERT INTO %s " +
                            "VALUES(null, ?, ?, ?, ?)", DEFAULT_TABLE);
        } else {
            query = String.format(
                    "UPDATE %s SET name=? , type=?, description=?, price=? " +
                            "WHERE id=?", DEFAULT_TABLE);
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemType);
            preparedStatement.setString(3, itemDescription);
            preparedStatement.setInt(4, price);
            if(!artifactId.equals("-1")) {
                preparedStatement.setInt(5, Integer.valueOf(artifactId));
            }
            SQLProcessManager.executeUpdate(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.ARTIFACTS.getName();
    }
}
