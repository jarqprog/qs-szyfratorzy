package school;

import application.CreatableDAO;
import application.Table;
import users.*;

import java.util.ArrayList;
import java.util.List;

public class SchoolController {

    public static List<GroupModel> getGroups() {
        return new GroupDAO().getAllObjects();
    }

    public static List<TeamModel> getTeams() {
        return new TeamDAO().getAllObjects();
    }

    public static List<String> getGroupNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.GROUPS.getName());
    }

    public static List<String> getTeamNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.TEAMS.getName());
    }

    public static void assignMentorToGroup(MentorModel mentor){
        SchoolView view = new SchoolView();
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

    public static void assignStudentToGroup(StudentModel student){
        SchoolView view = new SchoolView();
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

    public static void assignStudentToTeam(StudentModel student) {
        SchoolView view = new SchoolView();
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

    public static List<TeamModel> getTeamsByGroup(GroupModel group){
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

    public static List<StudentModel> getStudentsByGroup(GroupModel group) {
        return group.getStudents();
    }

    public static List<StudentModel> getStudentsByTeam(TeamModel team) {
        return team.getStudents();
    }

    public static List<StudentModel> getAllStudents() {
        return new StudentDAO().getAllObjects();
    }

    public static List<MentorModel> getAllMentors() {
        return new MentorDAO().getAllObjects();
    }

    public static StudentModel pickStudentFromList(List<StudentModel> students) {
        SchoolView view = new SchoolView();
        view.displayObjects(students);
        String chosenStudent = view.getUserInput("\nChoose student by id: ");
        for (StudentModel student : students){
            if (chosenStudent.equals(String.valueOf(student.getId()))){
                return student;
            }
        }
        return null;
    }

    private static TeamModel getDefaultTeam(){
        CreatableDAO dao = new TeamDAO();
        return dao.getObjectById(1);
    }

    public static MentorModel getMentorByUserChoice() {
        UsersView view = new UsersView();
        List<MentorModel> mentors = getAllMentors();
        view.displayMessage("Mentors:\n");
        view.displayUsers(mentors);
        String id = view.getUserInput("\nSelect mentor by id: ");
        for (MentorModel mentor : mentors) {
            if (id.equals(Integer.toString(mentor.getId()))) {
                return mentor;
            }
        }
        return null;
    }

    public static void createNewTeam(){
        boolean isDone = false;
        String teamName = "";
        SchoolView view = new SchoolView();
        while (!isDone){
            teamName = view.getUserInput("Enter team name(or 0 to exit): ");
            if (teamName.equals("0")){
                isDone = true;
                break;
            } else if (getTeamNames().contains(teamName)) {
                view.displayMessage("Team already exist...");
                continue;
            } else {
                TeamModel newTeam = new TeamModel(teamName);
                view.clearScreen();
                view.displayMessage("Team created: \n" + newTeam);
                isDone = true;
            }
        }
    }

    public static void createNewGroup(){
        boolean isDone = false;
        String groupName = "";
        SchoolView view = new SchoolView();
        while (!isDone){
            groupName = view.getUserInput("Enter group name(or 0 to exit): ");
            if (groupName.equals("0")){
                isDone = true;
                break;
            } else if (getGroupNames().contains(groupName)) {
                view.displayMessage("Group already exist...");
                continue;
            } else {
                GroupModel newGroup = new GroupModel(groupName);
                view.clearScreen();
                view.displayMessage("Group created: \n" + newGroup);
                isDone = true;
            }
        }
    }

}
