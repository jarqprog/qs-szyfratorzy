package model;

public class TeamFactoryImpl implements StudentSetFactory {

    public Team create(String name) {
        Team team = new Team(name);
        int id = ModelDaoFactory.getByType(TeamDAO.class).saveObjectAndGetId(team);
        team.setId(id);
        team.setInventory(new TeamInventory(id));
        return team;
    }

    public Team getDefault() {
        return new Team(1, "undefined");
    }
}
