package dao;

import managers.TemporaryManager;
import model.Artifact;
import model.Inventory;

import java.util.*;

public abstract class InventoryDAO implements PassiveModelDAO<Inventory> {

    protected String DEFAULT_TABLE;
    protected TemporaryManager dao;
    protected ArtifactDAO artifactDAO;


    public Map<Artifact,Integer> load(int ownerId) {
        final String query = String.format("SELECT artifact_id FROM %s WHERE owner_id=%s;",
                DEFAULT_TABLE, ownerId);
        dao = new TemporaryManager();
        Map<Artifact,Integer> inventory = new HashMap<>();
        List<String[]> dataCollection = dao.getData(query);
        if (dataCollection.size() > 0) {
            artifactDAO = new ArtifactDAO();
            Artifact artifact;
            int ID_INDEX = 0;
            List<Integer> usedArtifactsId = new ArrayList<>(5);
            for (String[] data : dataCollection) {
                int artifactId = Integer.parseInt(data[ID_INDEX]);
                if (!usedArtifactsId.contains(artifactId)) {
                    artifact = artifactDAO.getObjectById(artifactId);
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

    public void save(Inventory inventory) {
        int ownerId = inventory.getOwnerId();
        dao = new TemporaryManager();
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=%s;", DEFAULT_TABLE, ownerId);
        dao.inputData(clearQuery);
        if (! inventory.isEmpty()) {
            String query;
            for (Map.Entry<Artifact,Integer> entry : inventory.getStock().entrySet()) {
                Integer artifactId = entry.getKey().getId();
                Integer value = entry.getValue();
                for(int i = 0; i < value; i++) {
                    query = String.format("INSERT INTO %s " +
                            "VALUES(null, %s, %s);", DEFAULT_TABLE, ownerId, artifactId);
                    dao.inputData(query);
                }
            }
        }
    }
}
