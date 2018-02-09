package school;

import item.ArtifactModel;
import shop.InventoryModel;
import users.StudentDAO;

import java.util.ArrayList;
import java.util.List;

public class TeamModel extends StudentSetsModel{

    private InventoryModel inventory;

    public TeamModel(int id, String name) {
        super(id, name);
        inventory = new InventoryModel(id);
    }

    public TeamModel(String name) {
        super(name);
        this.id = saveNewObjectGetId();
        inventory = new InventoryModel(id);
    }

    public InventoryModel getInventory() {
        return inventory;
    }

    public void setInventory(InventoryModel inventory) {
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
