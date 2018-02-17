package dao;

import enums.Table;
import model.Team;

public class TeamDAO extends ActiveObjDAOImpl<Team> {

    private String name;

    public TeamDAO(){
        this.DEFAULT_TABLE = Table.TEAMS.getName();
    }

    public Team getOneObject(String[] teamData) {

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(teamData[ID_INDEX]);
        name = teamData[NAME_INDEX];

        return new Team(id, name);
    }

    public void save(Team team){
        String teamId = String.valueOf(team.getId());
        name = team.getName();
        String query;
        if(teamId.equals("-1")){
            query = String.format(
                    "INSERT INTO %s " +
                            "VALUES(null, '%s');",
                    DEFAULT_TABLE, name);
        } else{
            query = String.format(
                    "UPDATE %s SET name='%s' " +
                            "WHERE id=%s;", DEFAULT_TABLE, name, teamId);
        }
        dao = new DbManagerDAO();
        dao.inputData(query);
    }
}