package school;

import java.util.ArrayList;
import java.util.HashMap;

public class SchoolModel
{
    private HashMap<String, Integer> expirenceLevels;
    private ArrayList<Character> groups;
    private ArrayList<String> teams;

    public SchoolModel(){
        expirenceLevels = new HashMap<String, Integer>();
        groups = new ArrayList<Character>();
        teams = new ArrayList<String>();
    }

    public HashMap<String, Integer> getExpirenceLevels() {
        return expirenceLevels;
    }

    public void addExpirenceLevel(String level, Integer expirence) {
        this.expirenceLevels.put(level, expirence);
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