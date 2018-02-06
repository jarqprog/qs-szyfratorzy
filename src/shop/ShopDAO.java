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
    protected String DEFAULT_TABLE;
    private Date date;

    public ShopDAO(){ this.DEFAULT_TABLE = Table.STUDENTS_ARTIFACTS.getName(); }

    public List<String []> findArtifacts(int studentId) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artifact_id, id FROM %s " +
                                    "WHERE student_id = %s;", DEFAULT_TABLE, studentId);
        return dao.getData(query);
    }

    public List<String []> findTeamArtifacts(int teamId) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artifact_id, id FROM team_artifacts " +
                "WHERE team_id = %s;", teamId);
        return dao.getData(query);
    }

    public Map<ArtifactModel, Integer> loadInventory(List<String []> artifactsId) {
        dao = new DbManagerDAO();
        artifactDAO = new ArtifactDAO();
        Map<ArtifactModel, Integer> inventory = new HashMap<>();
        Integer value;

        for(String[] record : artifactsId) {
            int artifactId = Integer.parseInt(record[INDEX_ART_ID]);
            String query = String.format("SELECT * FROM artifacts " +
                    "WHERE id = %s;", artifactId);
            ArtifactModel artifact = artifactDAO.getOneObject(query);
            if(inventory.containsKey(artifact)) {
                value = inventory.get(artifact);
                inventory.replace(artifact, ++value);
            } else {
            inventory.put(artifact, 1);
            }
        }

        for(String[] record : artifactsId) {
            int id = Integer.parseInt(record[INDEX_STUDENTS_ARTIFACTS_ID]);
            String query = String.format("DELETE FROM %s " +
                    "WHERE id = %s;", DEFAULT_TABLE, id);
            dao.inputData(query);
        }
        return inventory;
    }

    public Map<ArtifactModel, Integer> loadStudentInventory(int studentId) {
        return loadInventory(findArtifacts(studentId));
    }

    public Map<ArtifactModel, Integer> loadTeamInventory(int teamId) {
        return loadInventory(findTeamArtifacts(teamId));
    }

    public void deleteFromInventory(int studentId, int artifactId) {
        List<String []> artifactsId = findArtifacts(studentId);
        for(String[] record : artifactsId) {
            int id = Integer.parseInt(record[INDEX_STUDENTS_ARTIFACTS_ID]);
            String query = String.format("DELETE FROM %s " +
                    "WHERE id = %s AND artifact_id = %s;", DEFAULT_TABLE, id, artifactId);
            dao.inputData(query);
            break;
        }
    }

    public void saveInventory(int studentId, List<ArtifactModel> inventory) {
        dao = new DbManagerDAO();
        int artifactId = -1;
        String query;
        for(ArtifactModel artifact : inventory) {
            artifactId = artifact.getId();
            query = String.format("INSERT INTO students_artifacts " +
                    "VALUES(null, %s, %s);", studentId, artifactId);
            dao.inputData(query);
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
