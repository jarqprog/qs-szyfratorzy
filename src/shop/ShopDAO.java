package shop;

import application.DbManagerDAO;
import application.Table;
import item.ArtifactModel;
import item.ArtifactDAO;

import java.util.ArrayList;
import java.util.List;


public class ShopDAO {

    private static final int INDEX_ART_ID = 0;
    private static final int INDEX_STUDENTS_ARTIFACTS_ID = 1;
    protected DbManagerDAO dao;
    protected ArtifactDAO artifactDAO;
    protected String DEFAULT_TABLE;

    public ShopDAO(){ this.DEFAULT_TABLE = Table.STUDENTS_ARTIFACTS.getName(); }

    public List<String []> findStudentArtifacts(int student_id) {
        dao = new DbManagerDAO();
        String query = String.format("SELECT artefact_id, id FROM %s " +
                                    "WHERE student_id = %s;", DEFAULT_TABLE, student_id );
        return dao.getData(query);
    }

    public List<ArtifactModel> loadInventory(List<String []> artifactsId) {
        dao = new DbManagerDAO();
        artifactDAO = new ArtifactDAO();
        ArrayList<ArtifactModel> inventory = new ArrayList<>();

        for(String[] record : artifactsId) {
            int artifactId = Integer.parseInt(record[INDEX_ART_ID]);
            String query = String.format("SELECT * FROM artifacts " +
                    "WHERE id = %s;", artifactId);
            ArtifactModel artifact = artifactDAO.getOneObject(query);
            inventory.add(artifact);
        }
        for(String[] record : artifactsId) {
            int id = Integer.parseInt(record[INDEX_STUDENTS_ARTIFACTS_ID]);
            String query = String.format("DELETE FROM %s " +
                    "WHERE id = %s;", DEFAULT_TABLE, id);
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
