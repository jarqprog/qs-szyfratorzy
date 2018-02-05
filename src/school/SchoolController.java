package school;

import application.Table;

import java.util.List;

public class SchoolController {

    private SchoolView view;

    public SchoolController() {
        this.view = new SchoolView();
    }

    public List<GroupModel> getGroups() {
        return new GroupDAO().getAllObjects();
    }

    public List<TeamModel> getTeams() {
        return new TeamDAO().getAllObjects();
    }

    public List<String> getGroupNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.GROUPS.getName());
    }

    public List<String> getTeamNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.TEAMS.getName());
    }

    public ExperienceLevels getExperienceLevels() {
        return new ExperienceLevels();
    }

    public AttendanceModel getAttendance(){
        return new AttendanceModel();
    }

    protected void executeNotImplementedInfo() {
        view.displayMessage("Not implemented yet");
        view.handlePause();
    }
}
