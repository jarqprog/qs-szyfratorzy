package school;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;

public class TeamDAO extends FactoryDAO {

    private String name;

    public TeamDAO(){
        this.DEFAULT_TABLE = Table.TEAMS.getName();
    }

    public TeamModel getOneObject(String[] teamData) {

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(teamData[ID_INDEX]);
        name = teamData[NAME_INDEX];

        return new TeamModel(id, name);
    }

    public <T> void saveObject(T t){
        TeamModel team = (TeamModel) t;
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