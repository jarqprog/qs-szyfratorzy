package dao;

import enums.Table;
import model.Team;

import java.sql.Connection;

public class TeamDAO extends StudentSetDAO<Team> {

    TeamDAO(Connection connection) {
        super(connection);
    }

    public Team extractModel(String[] teamData) {
        String name;
        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(teamData[ID_INDEX]);
        name = teamData[NAME_INDEX];

        return new Team(id, name);
    }
    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.TEAMS.getName();
    }
}