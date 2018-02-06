package school;

import item.ArtifactModel;
import users.StudentDAO;
import java.util.ArrayList;
import java.util.List;

public class TeamModel extends StudentSetsModel{

    private List<ArtifactModel> inventory;


    public TeamModel(int id, String name) {
        super(id, name);
        inventory = new ArrayList<>();
    }

    public TeamModel(String name) {
        super(name);
        inventory = new ArrayList<>();
        this.id = saveNewObjectGetId();
    }

    public List<ArtifactModel> getInventory() {
        return inventory;
    }

    public void setInventory(List<ArtifactModel> inventory) {
        this.inventory = inventory;
    }

    public int saveNewObjectGetId(){
        TeamDAO dao = new TeamDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void setStudents() {
        StudentDAO dao = new StudentDAO();
        final String query = String.format("SELECT * FROM students WHERE team_id=%s;", id);
        this.students = dao.getManyObjects(query);
    }

    public void saveObject(){
        TeamDAO dao = new TeamDAO();
        dao.saveObject(this);
    }

    public int size(){
        return students.size();
    }
}
