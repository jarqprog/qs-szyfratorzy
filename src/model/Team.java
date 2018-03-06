package model;

public class Team extends StudentSet {

    private TeamInventory inventory;

    Team(int id, String name) {
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
        this.students = ModelDaoFactory.getByType(StudentDAO.class)
                .getFilteredModelsByIntegerParameter("team_id", id);
    }

    public int size(){
        return students.size();
    }
}
