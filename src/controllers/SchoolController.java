package controllers;

import dao.*;
import model.Group;
import model.Mentor;
import model.Student;
import model.Team;
import dao.ActiveObjDAO;
import enums.Table;
import view.SchoolView;
import view.UsersView;

import java.util.ArrayList;
import java.util.List;

public class SchoolController {

    public static List<Group> getGroups() {
        return new GroupDAO().getAllObjects();
    }

    public static List<Team> getTeams() {
        return new TeamDAO().getAllObjects();
    }

    public static List<String> getGroupNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.GROUPS.getName());
    }

    public static List<String> getTeamNames() {
        return new SchoolDAO().getStudentsSetsNames(Table.TEAMS.getName());
    }

    public static void assignMentorToGroup(Mentor mentor){
        SchoolView view = new SchoolView();
        List<Group> groups = getGroups();
        boolean isMentorAssigned = false;
        String chosenGroupName = "";
        while (!isMentorAssigned && !chosenGroupName.equals("0")){
            view.displayObjects(groups);
            chosenGroupName = view.getUserInput("\nChoose group by name (or type 0 to exit): ");
            for (Group group : groups){
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

    public static void assignStudentToGroup(Student student){
        SchoolView view = new SchoolView();
        List<Group> groups = getGroups();
        boolean isStudentAssigned = false;
        String chosenGroupName = "";
        while (!isStudentAssigned && !chosenGroupName.equals("0")){
            view.displayObjects(groups);
            chosenGroupName = view.getUserInput("\nChoose group by name (or type 0 to exit): ");
            for (Group group : groups){
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

    public static void assignStudentToTeam(Student student) {
        SchoolView view = new SchoolView();
        List<Team> teams = getTeamsByGroup(student.getGroup());
        boolean isStudentAssigned = false;
        String chosenTeamName = "";
        while (!isStudentAssigned && !chosenTeamName.equals("0")){
            view.displayObjects(teams);
            chosenTeamName = view.getUserInput("\nChoose team by name (or type 0 to exit): ");
            for (Team team : teams){
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

    public static List<Team> getTeamsByGroup(Group group){
        List<Team> teams = getTeams();
        List<Team> teamsByGroup = new ArrayList<>();
        for (Team team : teams){
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

    public static List<Student> getStudentsByGroup(Group group) {
        return group.getStudents();
    }

    public static List<Student> getStudentsByTeam(Team team) {
        return team.getStudents();
    }

    public static List<Student> getAllStudents() {
        return new StudentDAO().getAllObjects();
    }

    public static List<Mentor> getAllMentors() {
        return new MentorDAO().getAllObjects();
    }

    public static Student pickStudentFromList(List<Student> students) {
        SchoolView view = new SchoolView();
        view.displayObjects(students);
        String chosenStudent = view.getUserInput("\nChoose student by id: ");
        for (Student student : students){
            if (chosenStudent.equals(String.valueOf(student.getId()))){
                return student;
            }
        }
        return null;
    }

    private static Team getDefaultTeam(){
        ActiveObjDAO<Team> dao = new TeamDAO();
        return dao.getObjectById(1);
    }

    public static Mentor getMentorByUserChoice() {
        UsersView view = new UsersView();
        List<Mentor> mentors = getAllMentors();
        view.displayMessage("\nMentors:\n");
        view.displayUsers(mentors);
        String id = view.getUserInput("\nSelect mentor by id: ");
        for (Mentor mentor : mentors) {
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
            teamName = view.getUserInput("\nEnter team name (or 0 to exit): ");
            if (teamName.equals("0")){
                isDone = true;
                break;
            } else if (getTeamNames().contains(teamName)) {
                view.displayMessage("   - Team already exist...");
                continue;
            } else {
                Team newTeam = new Team(teamName);
                view.clearScreen();
                view.displayMessage("\n - Team created: \n" + newTeam);
                isDone = true;
            }
        }
    }

    public static void createNewGroup(){
        boolean isDone = false;
        String groupName = "";
        SchoolView view = new SchoolView();
        while (!isDone){
            groupName = view.getUserInput("\nEnter group name (or 0 to exit): ");
            if (groupName.equals("0")){
                isDone = true;
                break;
            } else if (getGroupNames().contains(groupName)) {
                view.displayMessage("   - Group already exist...");
//                continue;
            } else {
                Group newGroup = new Group(groupName);
                view.clearScreen();
                view.displayMessage("\n - Group created: \n" + newGroup);
                isDone = true;
            }
        }
    }

    public static void checkAttendance(Mentor mentor){
        SchoolView view = new SchoolView();
        for (Student student : getStudentsByGroup(mentor.getGroup())){
            boolean isPresenceChecked = false;
            while (!isPresenceChecked){
                String userInput = view.getUserInput(String.format("    - Is %s present (y/anything else): ", student.getFullName()));
                boolean isPresent = userInput.equals("y");
                student.addAttendance(isPresent);
                isPresenceChecked = true;
            }
        }
    }
}
