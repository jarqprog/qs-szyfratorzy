package model;

import dao.DaoFactory;
import dao.StudentDAO;

public class Team extends StudentSets {

    private TeamInventory inventory;

    public Team(int id, String name) {
        super(id, name);
        inventory = new TeamInventory(id);
    }

    Team(String name) {
        super(name);
    }

    public TeamInventory getInventory() {
        inventory.setStock();
        return inventory;
    }

    public void setInventory(TeamInventory inventory) {
        this.inventory = inventory;
    }

    public void setStudents() {
        StudentDAO dao = DaoFactory.getByType(StudentDAO.class);
        final String query = String.format("SELECT * FROM students WHERE team_id=%s;", id);
        this.students = dao.getManyObjects(query);
    }

    public int size(){
        return students.size();
    }
}
