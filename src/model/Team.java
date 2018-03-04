package model;

import dao.DaoFactory;
import dao.StudentDAO;

public class Team extends StudentSet {

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
        this.students = DaoFactory.getByType(StudentDAO.class)
                .getFilteredModelsByIntegerParameter("team_id", id);
    }

    public int size(){
        return students.size();
    }
}
