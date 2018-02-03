package school;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class SchoolModel {

    private String name;
    private List<GroupModel> groups;
    private List<TeamModel> teams;
    private List<String> groupNames;
    private List<String> teamNames;
    private ExperienceLevels experienceLevels;

    public SchoolModel(){
        name = "Codecool Krakow";
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public List<GroupModel> getGroups() {
        groups = new ArrayList<>();
        return groups;
    }

    public List<TeamModel> getTeams() {
        teams = new ArrayList<>();
        return teams;
    }

    public List<String> getGroupNames() {
        groupNames = new ArrayList<>();
        return groupNames;
    }

    public List<String> getTeamNames() {
        teamNames = new ArrayList<>();
        return teamNames;
    }

    public ExperienceLevels getExperienceLevels() {
        experienceLevels = new ExperienceLevels();
        return experienceLevels;
    }

    public void setExperienceLexels(Map<String, Integer> newExpLevels) {
        experienceLevels.setLevels(newExpLevels);
    }
}
