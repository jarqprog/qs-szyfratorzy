package shop;

import application.DbManagerDAO;
import item.ArtifactModel;
import item.ArtifactDAO;

import java.util.ArrayList;
import java.util.List;


public class InventoryDAO {
    protected DbManagerDAO dao;
    protected ArtifactDAO artifactDAO;

    public List<String []> findStudentArtifacts(int student_id) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artefact_id, id FROM students_artifacts " +
                                    "WHERE student_id = %s;", student_id );
        return dao.getData(query);
    }

    public ArrayList<ArtifactModel> loadInventory(List<String []> artifactsId) {
        dao = new DbManagerDAO();
        artifactDAO = new ArtifactDAO();
        ArrayList<ArtifactModel> inventory = new ArrayList<>();

        for(String[] record : artifactsId) {
            int artifactId = Integer.parseInt(record[0]);
            String query = String.format("SELECT * FROM artifacts " +
                    "WHERE id = %s;", artifactId);
            ArtifactModel artifact = artifactDAO.getOneObject(query);
            inventory.add(artifact);
        }
        for(String[] record : artifactsId) {
            int id = Integer.parseInt(record[1]);
            String query = String.format("DELETE FROM students_artifacts " +
                    "WHERE id = %s;", id);
            dao.inputData(query);
        }
        return inventory;
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
}
