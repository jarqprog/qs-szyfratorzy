package shop;

import application.DataTool;
import application.DbManagerDAO;
import item.ArtifactDAO;
import item.ArtifactModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class InventoryDAO {

    protected String DEFAULT_TABLE;
    protected DbManagerDAO dao;
    protected ArtifactDAO artifactDAO;

    public Map<ArtifactModel,Integer> loadInventory(int ownerId) {
        final String query = String.format("SELECT artifact_id FROM %s WHERE owner_id=%s;",
                DEFAULT_TABLE, ownerId);

        List<String[]> dataCollection = dao.getData(query);
        if (dataCollection.size() > 0) {
            artifactDAO = new ArtifactDAO();
            ArtifactModel artifact;
            Integer value;
            int ID_INDEX = 0;
            Map<ArtifactModel, Integer> inventory = new HashMap<>();
            for (String[] data : dataCollection) {
                int artifactId = Integer.parseInt(data[ID_INDEX]);
                artifact = artifactDAO.getObjectById(artifactId);
                if (DataTool.checkIfMapContainsItem(artifact, inventory)) {
                    value = inventory.get(artifact);
                    inventory.put(artifact, ++value);
                } else {
                    inventory.put(artifact, 1);
                }
                return inventory;
            }
        }
        return null;
    }

    public void saveInventory(InventoryModel inventory) {
        int ownerId = inventory.getOwnerId();
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=%s;", DEFAULT_TABLE, ownerId);
        dao.inputData(clearQuery);
        if (! inventory.isEmpty()) {
            String query;
            dao = new DbManagerDAO();
            Set<ArtifactModel> keys = inventory.getStock().keySet();
            for(ArtifactModel artifact : keys) {
                int artifactId = artifact.getId();
                Integer value = inventory.getStock().get(artifact);
                for(int i = 0; i < value; i++) {
                    query = String.format("INSERT INTO %s " +
                            "VALUES(null, %s, %s);", DEFAULT_TABLE, ownerId, artifactId);
                    dao.inputData(query);
                }
            }
        }
    }
}
