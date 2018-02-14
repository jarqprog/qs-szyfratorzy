package factory;

import dao.TeamDAO;
import model.Team;
import model.TeamInventory;

public class TeamFactoryImpl implements StudentSetFactory {

    public Team create(String name) {
        Team team = new Team(name);
        int id = new TeamDAO().saveObjectAndGetId(team);
        team.setId(id);
        team.setInventory(new TeamInventory(id));
        return team;
    }

    public Team createUndefined() {
        return new Team(1, "undefined");
    }
}
