package controllers;

import dao.*;
import factory.AbsObjectFactory;
import model.GroupFactoryImpl;
import model.TeamFactoryImpl;
import model.Group;
import model.Mentor;
import model.Student;
import model.Team;
import dao.ActiveModelDAO;
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
            chosenGroupName = view.getUserInput("Choose group by name (or type 0 to exit): ");
            for (Group group : groups){
                if (chosenGroupName.equals(group.getName())){
                    mentor.setGroup(group);
                    isMentorAssigned = true;
                    break;
                }
            }
            if (!isMentorAssigned && !chosenGroupName.equals("0")){
                view.displayMessageInNextLine("- there is no such group...");
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
            chosenGroupName = view.getUserInput("Choose group by name (or type 0 to exit): ");
            for (Group group : groups){
                if (chosenGroupName.equals(group.getName())){
                    student.setGroup(group);
                    view.displayMessageInNextLine("- student moved to group: " + group.getName());
                    if (student.getTeam().getId() != 1){
                        student.setTeam(getDefaultTeam());
                        view.displayMessageInNextLine("- student moved to undefined team...");
                    }
                    isStudentAssigned = true;
                    break;
                }
            }
            if (!isStudentAssigned && !chosenGroupName.equals("0")){
                view.displayMessageInNextLine("- there is no such group...");
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
            chosenTeamName = view.getUserInput("Choose team by name (or type 0 to exit): ");
            for (Team team : teams){
                if (chosenTeamName.equals(team.getName())){
                    student.setTeam(team);
                    view.displayMessageInNextLine("- student moved to team: " + team.getName());
                    isStudentAssigned = true;
                    break;
                }
            }
            if (!isStudentAssigned && !chosenTeamName.equals("0")){
                view.displayMessageInNextLine("- there is no such group...");
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
        String id = view.getUserInput("Choose student by id: ");
        view.drawNextLine();
        return students.stream()
                .filter(s -> String.valueOf(s.getId()).equals(id))
                .findAny()
                .orElse(null);
    }

    private static Team getDefaultTeam(){
        ActiveModelDAO<Team> dao = new TeamDAO();
        return dao.getObjectById(1);
    }

    public static Mentor getMentorByUserChoice() {
        UsersView view = new UsersView();
        List<Mentor> mentors = getAllMentors();
        view.displayMessageInNextLine("Mentors:\n");
        view.displayObjects(mentors);
        String id = view.getUserInput("Select mentor by id: ");
        view.drawNextLine();
        return mentors.stream()
                .filter(m -> String.valueOf(m.getId()).equals(id))
                .findAny()
                .orElse(null);
    }

    public static void createNewTeam(){
        boolean isDone = false;
        String teamName;
        SchoolView view = new SchoolView();
        while (!isDone){
            view.clearScreen();
            teamName = view.getUserInput("Enter team name (or 0 to exit): ");
            if (teamName.equals("0")){
                isDone = true;
            } else if (getTeamNames().contains(teamName)) {
                view.displayMessageInNextLine("- Team already exist...");
            } else {
                Team team = AbsObjectFactory.get(TeamFactoryImpl.class)
                        .create(teamName);

                view.clearScreen();
                view.displayMessageInNextLine("- Team created: \n");
                view.displayObject(team);
                isDone = true;
            }
        }
    }

    public static void createNewGroup(){
        boolean isDone = false;
        String groupName;
        SchoolView view = new SchoolView();
        while (!isDone){
            view.clearScreen();
            groupName = view.getUserInput("Enter group name (or 0 to exit): ");
            if (groupName.equals("0")){
                break;
            } else if (getGroupNames().contains(groupName)) {
                view.displayMessageInNextLine("- group already exist...");
            } else {
                Group newGroup = AbsObjectFactory.get(GroupFactoryImpl.class)
                        .create(groupName);
                view.clearScreen();
                view.displayMessageInNextLine("- group created: \n");
                view.displayObject(newGroup);
                isDone = true;
            }
        }
    }

    public static void checkAttendance(Mentor mentor){
        SchoolView view = new SchoolView();
        for (Student student : getStudentsByGroup(mentor.getGroup())){
            boolean isPresenceChecked = false;
            while (!isPresenceChecked){
                view.clearScreen();
                String userInput = view.getUserInput(String.format("- Is %s present (y/anything else): ", student.getFullName()));
                boolean isPresent = userInput.equals("y");
                student.addAttendance(isPresent);
                isPresenceChecked = true;
            }
        }
    }
}
