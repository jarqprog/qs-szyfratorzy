package school;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;
import users.StudentModel;

public class TeamDAO {

    private DbManagerDAO dao;
    private final String DEFAULT_TABLE = Table.TEAMS.getName();
    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;
    private int teamId;
    private String name;

    public List<TeamModel> getManyObjects(List<String[]> dataCollection) {

        List<TeamModel> teams = new ArrayList<TeamModel>();
        for (String[] record : dataCollection) {
            TeamModel team = getOneObject(record);
            teams.add(team);
        }
        return teams;
    }

    public List<TeamModel> getManyObjects(String query) {
        DbManagerDAO dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        List<TeamModel> teams = new ArrayList<TeamModel>();
        for (String[] record : dataCollection) {
            TeamModel team = getOneObject(record);
            teams.add(team);
        }
        return teams;
    }

    public TeamModel getOneObject(String[] teamData) {

        int id = Integer.parseInt(teamData[ID_INDEX]);
        String name = teamData[NAME_INDEX];
        // temp
        List<StudentModel> students = new ArrayList<StudentModel>();

        return new TeamModel(id, name, students);
    }

    public TeamModel getOneObject(String query) {

        dao = new DbManagerDAO();
        String[] teamData = dao.getData(query).get(0);
        int id = Integer.parseInt(teamData[ID_INDEX]);
        name = teamData[NAME_INDEX];
        List<StudentModel> students = new ArrayList<StudentModel>();

        return new TeamModel(id, name, students);
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
                            "WHERE id=%s;", DEFAULT_TABLE, name);
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