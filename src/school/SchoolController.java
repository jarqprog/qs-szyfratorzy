package school;

import application.CreatableDAO;
import application.Table;
import users.MentorModel;
import users.StudentModel;
import users.StudentDAO;

import java.util.ArrayList;
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
                    view.displayMessage("Student moved to group: " + group.getName());
                    if (student.getTeam().getId() != 1){
                        student.setTeam(getDefaultTeam());
                        view.displayMessage("Student moved to undefined team...");
                    }
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
        List<TeamModel> teams = getTeamsByGroup(student.getGroup());
        boolean isStudentAssigned = false;
        String chosenTeamName = "";
        while (!isStudentAssigned && !chosenTeamName.equals("0")){
            view.displayObjects(teams);
            chosenTeamName = view.getUserInput("\nChoose team by name (or type 0 to exit): ");
            for (TeamModel team : teams){
                if (chosenTeamName.equals(team.getName())){
                    student.setTeam(team);
                    view.displayMessage("\nStudent moved to team: " + team.getName());
                    isStudentAssigned = true;
                    break;
                }
            }
            if (!isStudentAssigned && !chosenTeamName.equals("0")){
                view.displayMessage("   - There is no such group...");
            }
        }
    }

    public List<TeamModel> getTeamsByGroup(GroupModel group){
        List<TeamModel> teams = getTeams();
        List<TeamModel> teamsByGroup = new ArrayList<>();
        for (TeamModel team : teams){
            team.setStudents();
            if (team.size() == 0){
                teamsByGroup.add(team);
            } else {
                int groupId = team.getStudents().get(0).getGroup().getId();
                if (groupId == group.getId()){
                    teamsByGroup.add(team);
                }
            } 
        }
        return teamsByGroup;
    }

    public List<StudentModel> getStudentsByGroup(GroupModel group) {
        return group.getStudents();
    }

    public List<StudentModel> getStudentsByTeam(TeamModel team) {
        return team.getStudents();
    }

    public List<StudentModel> getAllStudents() {
        return new StudentDAO().getAllObjects();
    }

    public StudentModel pickStudentFromList(List<StudentModel> students) {
        view.displayObjects(students);
        String chosenStudent = view.getUserInput("\nChoose student by id: ");
        for (StudentModel student : students){
            if (chosenStudent.equals(String.valueOf(student.getId()))){
                return student;
            }
        }
        return null;
    }

    private TeamModel getDefaultTeam(){
        CreatableDAO dao = new TeamDAO();
        return dao.getObjectById(1);
    }
}
