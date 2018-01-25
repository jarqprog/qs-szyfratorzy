package school;

import java.util.ArrayList;
import java.util.List;

import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class TeamDAO extends FactoryDAO {

    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;
    private String name;

    public TeamDAO(){
        this.DEFAULT_TABLE = Table.TEAMS.getName();
    }

    public List<TeamModel> getManyObjects(List<String[]> dataCollection) {
        List<TeamModel> teams = new ArrayList<>();
        for (String[] record : dataCollection) {
            TeamModel team = getOneObject(record);
            teams.add(team);
        }
        return teams;
    }

    public List<TeamModel> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public TeamModel getOneObject(String[] teamData) {
        int id = Integer.parseInt(teamData[ID_INDEX]);
        String name = teamData[NAME_INDEX];
        List<StudentModel> students = new ArrayList<>();
        return new TeamModel(id, name, students);
    }

    public TeamModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] teamData = dao.getData(query).get(0);
        return getOneObject(teamData) ;
    }

    public void saveObject(TeamModel team) {
        String teamId = String.valueOf(team.getId());
        name = team.getName();
        String query;
        if(teamId.equals("-1")){
            System.out.println(teamId);
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
    public void saveObjects(List<TeamModel> teams){
        for(TeamModel team : teams) {
            saveObject(team);
        }
    }
}