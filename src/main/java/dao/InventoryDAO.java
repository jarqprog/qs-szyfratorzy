package dao;

import managers.SQLProcessManager;
import model.Artifact;
import model.ArtifactDAO;
import model.Inventory;
import model.ModelDaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class InventoryDAO<T extends Inventory>  extends PassiveModelDAOImpl<T> {

    protected InventoryDAO(Connection connection) {
        super(connection);
    }

    public Map<Artifact,Integer> load(int ownerId) {
        Map<Artifact, Integer> inventory = new HashMap<>();
        List<String[]> dataCollection = getArtifactsData(ownerId);
        if (dataCollection.size() > 0) {
            ActiveModelDAO<Artifact> artifactDao = ModelDaoFactory.getByType(ArtifactDAO.class);
            Artifact artifact;
            int ID_INDEX = 0;
            List<Integer> usedArtifactsId = new ArrayList<>(5);
            for (String[] data : dataCollection) {
                int artifactId = Integer.parseInt(data[ID_INDEX]);
                if (!usedArtifactsId.contains(artifactId)) {
                    artifact = artifactDao.getModelById(artifactId);
                    inventory.put(artifact, 1);
                } else {
                    Set<Artifact> items = inventory.keySet();
                    for (Artifact inStockItem : items) {
                        if (inStockItem.getId() == artifactId) {
                            Integer quantity = inventory.get(inStockItem);
                            inventory.put(inStockItem, ++quantity);
                        }
                    }
                }
                usedArtifactsId.add(artifactId);
            }
        }
        return inventory;
    }

    public boolean saveModel(T inventory) {
        int ownerId = inventory.getOwnerId();
        try {
            clearInventory(ownerId);
            if (! inventory.isEmpty()) {
                String query = String.format("INSERT INTO %s VALUES(null, ?, ?)",
                                            getDefaultTable());
                PreparedStatement preparedStatement;
                preparedStatement = getConnection().prepareStatement(query);
                for (Map.Entry<Artifact,Integer> entry : inventory.getStock().entrySet()) {
                    Integer artifactId = entry.getKey().getId();
                    Integer value = entry.getValue();
                    for(int i = 0; i < value; i++) {
                        preparedStatement.setInt(1, ownerId);
                        preparedStatement.setInt(2, artifactId);
                        preparedStatement.addBatch();
                    }

                }
                SQLProcessManager.executeBatch(preparedStatement, getConnection());
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<String[]> getArtifactsData(int ownerId) {
        String query = String.format("SELECT artifact_id FROM %s WHERE owner_id=?",
                getDefaultTable());
        PreparedStatement preparedStatement = null;
        try {

            ResultSet resultSet;
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, ownerId);
            resultSet = preparedStatement.executeQuery();
            return SQLProcessManager.getObjectsDataCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLProcessManager.closePreparedStatement(preparedStatement);
        }
        return new ArrayList<>();
    }

    private void clearInventory(int ownerId) throws SQLException {
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=?", getDefaultTable());
        PreparedStatement preparedStatement = getConnection().prepareStatement(clearQuery);
        preparedStatement.setInt(1, ownerId);
        SQLProcessManager.executeUpdate(preparedStatement);
    }
}
