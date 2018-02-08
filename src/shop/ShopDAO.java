package shop;

import application.DbManagerDAO;
import application.Table;
import item.ArtifactModel;
import item.ArtifactDAO;

import java.util.*;


public class ShopDAO {

    private static final int INDEX_ART_ID = 0;
    private static final int INDEX_STUDENTS_ARTIFACTS_ID = 1;
    protected DbManagerDAO dao;
    protected ArtifactDAO artifactDAO;
    protected String DEFAULT_TABLE1;
    protected String DEFAULT_TABLE2;
    private Date date;

    public ShopDAO(){ this.DEFAULT_TABLE1 = Table.STUDENTS_ARTIFACTS.getName();
                    this.DEFAULT_TABLE2 = Table.TEAMS_ARTIFACTS.getName();
    }

    public List<String []> findArtifacts(int studentId) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artifact_id, id FROM %s " +
                                    "WHERE student_id = %s;", DEFAULT_TABLE1, studentId);
        return dao.getData(query);
    }

    public List<String []> findTeamArtifacts(int teamId) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artifact_id, id FROM team_artifacts " +
                "WHERE team_id = %s;", teamId);
        return dao.getData(query);
    }

    public Map<ArtifactModel, Integer> loadInventory(List<String []> artifactsId, String tableName) {
        dao = new DbManagerDAO();
        artifactDAO = new ArtifactDAO();
        Map<ArtifactModel, Integer> inventory = new HashMap<>();
        Integer value;

        for(String[] record : artifactsId) {
            int artifactId = Integer.parseInt(record[INDEX_ART_ID]);
            String query = String.format("SELECT * FROM artifacts " +
                    "WHERE id = %s;", artifactId);
            ArtifactModel artifact = artifactDAO.getOneObject(query);
            if (inventory.isEmpty()) {
                inventory.put(artifact, 1);
            } else {
                for (ArtifactModel key : inventory.keySet()) {
                    if (key.getId() == artifact.getId()) {
                        value = inventory.get(key);
                        inventory.put(key, ++value);
                    } else {
                        inventory.put(artifact, 1);
                    }
                }
            }
        }

        for(String[] record : artifactsId) {
            int id = Integer.parseInt(record[INDEX_STUDENTS_ARTIFACTS_ID]);
            String query = String.format("DELETE FROM %s " +
                    "WHERE id = %s;", tableName, id);
            dao.inputData(query);
        }

        return inventory;
    }

    public Map<ArtifactModel, Integer> loadStudentInventory(int studentId) {
        return loadInventory(findArtifacts(studentId), DEFAULT_TABLE1);
    }

    public Map<ArtifactModel, Integer> loadTeamInventory(int teamId) {
        return loadInventory(findTeamArtifacts(teamId), DEFAULT_TABLE2);
    }

    public void deleteFromInventory(int id, int artifactId) {
        List<String []> artifactsId = findArtifacts(id);
        for(String[] record : artifactsId) {
            int index = Integer.parseInt(record[INDEX_STUDENTS_ARTIFACTS_ID]);
            String query = String.format("DELETE FROM %s " +
                    "WHERE id = %s AND artifact_id = %s;", DEFAULT_TABLE1, index, artifactId);
            dao.inputData(query);
            break;
        }
    }

    public void saveInventory(int id, String tableName, InventoryModel inventory) {
        dao = new DbManagerDAO();
        int artifactId = -1;
        Set<ArtifactModel> keys = inventory.getStock().keySet();
        String query;
        for(ArtifactModel artifact : keys) {
            artifactId = artifact.getId();
            Integer value = (Integer) inventory.getStock().get(artifact);
            for(int i = 0; i < value; i++) {
                query = String.format("INSERT INTO %s " +
                        "VALUES(null, %s, %s);", tableName, id, artifactId);
                dao.inputData(query);
            }
        }
    }

    public void saveStudentTransaction(int studentID, int artifactID) {
        dao = new DbManagerDAO();
        date = new Date();
        String transactionDate = date.toString();
        String query = String.format("INSERT INTO students_transactions " +
                        "VALUES(null, %s, %s, '%s');", studentID, artifactID, transactionDate);
        dao.inputData(query);
    }

}
