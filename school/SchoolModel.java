package school;

import java.util.ArrayList;
import java.util.HashMap;

public class SchoolModel
{
    private HashMap<String, Integer> expirenceLevels;
    private ArrayList<Character> groups;
    private ArrayList<String> teams;

    public HashMap<String, Integer> getExpirenceLevels() {
        return expirenceLevels;
    }

    public void setExpirenceLevels(HashMap<String, Integer> newLevelsOfExpirence) {
        this.expirenceLevels = newLevelsOfExpirence;
    }

    public ArrayList<Character> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Character> newGroups) {
        this.groups = newGroups;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> newTeams) {
        this.teams = newTeams;
    }
}