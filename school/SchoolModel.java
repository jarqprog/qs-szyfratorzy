package school;

import java.util.ArrayList;
import java.util.HashMap;

public class SchoolModel
{
    private HashMap<String, Integer> experienceLevels;
    private ArrayList<Character> groups;
    private ArrayList<String> teams;

    public SchoolModel(){
        experienceLevels = new HashMap<String, Integer>();
        groups = new ArrayList<Character>();
        teams = new ArrayList<String>();
    }

    public HashMap<String, Integer> getExperienceLevels() {
        return experienceLevels;
    }

    public void addExperienceLevel(String level, Integer experience) {
        this.experienceLevels.put(level, experience);
    }

    public ArrayList<Character> getGroups() {
        return groups;
    }

    public void addGroup(Character newGroup) {
        this.groups.add(newGroup);
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void addTeam(String newTeam) {
        this.teams.add(newTeam);
    }
}
