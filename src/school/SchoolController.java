package school;

import application.Table;
import users.MentorModel;
import users.StudentModel;
import users.UserModel;

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

    public void assignMentorToGroup(MentorModel mentor){
        List<GroupModel> groups = getGroups();
        boolean isMentorAssigned = false;
        String chosenGroupName = "";
        while (!isMentorAssigned && !chosenGroupName.equals("0")){
            view.displayObjects(groups);
            chosenGroupName = view.getUserInput("\nChoose group by name (or type 0 to exit): ");
            for (GroupModel group : groups){
                if (chosenGroupName.equals(group.getName())){
                    mentor.setGroup(group);
                    isMentorAssigned = true;
                    break;
                }
            }
            if (!isMentorAssigned && !chosenGroupName.equals("0")){
                view.displayMessage("   - There is no such group...");
            }
        }
    }

    public void assignStudentToGroup(StudentModel student){
        List<GroupModel> groups = getGroups();
        boolean isStudentAssigned = false;
        String chosenGroupName = "";
        while (!isStudentAssigned && !chosenGroupName.equals("0")){
            view.displayObjects(groups);
            chosenGroupName = view.getUserInput("\nChoose group by name (or type 0 to exit): ");
            for (GroupModel group : groups){
                if (chosenGroupName.equals(group.getName())){
                    student.setGroup(group);
                    isStudentAssigned = true;
                    break;
                }
            }
            if (!isStudentAssigned && !chosenGroupName.equals("0")){
                view.displayMessage("   - There is no such group...");
            }
        }
    }

    public void assignStudentToTeam(StudentModel student) {
        List<TeamModel> teams = getTeams();
        boolean isStudentAssigned = false;
        String chosenGroupName = "";
        while (!isStudentAssigned && !chosenGroupName.equals("0")){
            view.displayObjects(teams);
            chosenGroupName = view.getUserInput("\nChoose group by name (or type 0 to exit): ");
            for (TeamModel team : teams){
                if (chosenGroupName.equals(team.getName())){
                    student.setTeam(team);
                    isStudentAssigned = true;
                    break;
                }
            }
            if (!isStudentAssigned && !chosenGroupName.equals("0")){
                view.displayMessage("   - There is no such group...");
            }
        }
    }
}
