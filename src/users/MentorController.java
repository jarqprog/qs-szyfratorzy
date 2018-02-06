package users;

import item.ArtifactDAO;
import item.ArtifactModel;
import item.QuestDAO;
import item.QuestModel;
import school.SchoolController;

import java.util.List;

import application.CreatableDAO;

public class MentorController extends UserController{
    MentorView view;
    MentorModel mentor;
    MentorDAO dao;

    public MentorController(MentorModel mentorModel){
        mentor = mentorModel;
        view = new MentorView();
        dao = new MentorDAO();

    }

    public void handleMainMenu(){
        boolean isDone = false;
        while(! isDone){
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "0"};
            view.clearScreen();
            view.displayMenu();
            String userChoice = view.getMenuChoice(correctChoices);
            view.clearScreen();
            switch(userChoice){
                case "1":
                    showProfile(mentor);
                    break;
                case "2":
                    createStudent();
                    break;
                case "3":
                    assignStudentToGroup();
                    break;
                case "4":
                    assignStudentToTeam();
                    break;
                case "5":
                    createQuest();
                    break;
                case "6":
                    editQuest();
                    break;
                case "7":
                    createArtifact();
                    break;
                case "8":
                    editArtifact();
                    break;
                case "9":
                    markStudentQuest();
                    break;
                case "10":
                    displayStudentWallet();
                    break;
                case "11":
                    markStudentArtifacts();
                    break;
                case "12":
                    showStudentsFromMyGroup();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }

    private void createQuest() {
        String name = view.getUserInput("Enter quest name: ");
        String description = view.getUserInput("Enter quest description: ");
        int reward = view.getValue();
        QuestModel quest = new QuestModel(name, description, reward);
        view.displayMessage("You've created something new:\n" + quest);
    }

    private void editQuest() {

        List<QuestModel> quests = getQuests();
        view.displayElementsOfCollection(prepareObjectsToDisplay(quests));
        String input = view.getUserInput("Enter id of quest which you would like to edit: ");
        for(QuestModel quest : quests) {
            if(String.valueOf(quest.getId()).equals(input)) {
                handleQuestEditMenu(quest);
                break;
            }
        }

    }

    private void createStudent(){
        String firstName = view.getUserInput("Enter first name: ");
        String lastName = view.getUserInput("Enter last name: ");
        String password = view.getUserInput("Enter password: ");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        view.displayMessage("Student created successfully! \n" + newStudent.toString());
    }

    private void createArtifact(){
        String name = view.getUserInput("Enter artifact name: ");
        String description = view.getUserInput("Enter artifact description: ");
        int price = view.getValue();
        ArtifactModel artifact = new ArtifactModel(name, description, price);
        view.displayMessage("You've created something new:\n" + artifact);
        }

    private void editArtifact(){
        List<ArtifactModel> artifacts = getArtifacts();
        view.displayElementsOfCollection(prepareObjectsToDisplay(artifacts));
        String input = view.getUserInput("Enter id of artifact which you would like to edit: ");
        for(ArtifactModel artifact : artifacts) {
            if(String.valueOf(artifact.getId()).equals(input)) {
                handleArtifactEditMenu(artifact);
                break;
            }
        }
    }

    private void handleArtifactEditMenu(ArtifactModel artifact){
        boolean isDone = false;
        while(! isDone){
            String[] correctChoices = {"1", "2", "3", "4", "0"};
            view.clearScreen();
            view.displayArtifactMenu();
            String userChoice = view.getMenuChoice(correctChoices);
            view.clearScreen();
            switch(userChoice){
                case "1":
                    artifact.setName(view.getUserInput("Enter new artifact name: "));
                    break;
                case "2":
                    artifact.setType(view.getUserInput("Enter new artifact type: ").charAt(0));
                    break;
                case "3":
                    artifact.setDescription(view.getUserInput("Enter new artifact description: "));
                    break;
                case "4":
                    artifact.setPrice(view.getValue());
                    break;
                case "0":
                    ArtifactDAO dao = new ArtifactDAO();
                    dao.saveObject(artifact);
                    isDone = true;
                    break;
            }
            if(! isDone){
               view.displayMessage(artifact.toString());
               view.handlePause();
            }
        }
    }

    private void handleQuestEditMenu(QuestModel quest){
        boolean isDone = false;
        while(! isDone){
            String[] correctChoices = {"1", "2", "3", "4", "0"};
            view.clearScreen();
            view.displayQuestMenu();
            String userChoice = view.getMenuChoice(correctChoices);
            view.clearScreen();
            switch(userChoice){
                case "1":
                    quest.setName(view.getUserInput("Enter new quest name: "));
                    break;
                case "2":
                    quest.setType(view.getUserInput("Enter new quest type: ").charAt(0));
                    break;
                case "3":
                    quest.setDescription(view.getUserInput("Enter new quest description: "));
                    break;
                case "4":
                    quest.setReward(view.getValue());
                    break;
                case "0":
                    QuestDAO dao = new QuestDAO();
                    dao.saveObject(quest);
                    isDone = true;
                    break;
            }
            if(! isDone){
                view.displayMessage(quest.toString());
                view.handlePause();
            }
        }
    }

    private void markStudentQuest() {
        executeNotImplementedInfo();
    }

    private void displayStudentWallet() {
        executeNotImplementedInfo();
    }

    private void markStudentArtifacts() {
        executeNotImplementedInfo();
    }

    private void showStudentsFromMyGroup() {
        List<StudentModel> students = mentor.getGroup().getStudents();
        view.displayMessage("Your students:\n");
        view.displayUsersWithDetails(students);
    }

    private void assignStudentToGroup(){
        StudentModel student = pickStudent();
        if (student != null){
            new SchoolController().assignStudentToGroup(student);
            view.displayMessage("Done");
        } else {
            view.displayMessage("Student does not exist...");
        }
    }

    private void assignStudentToTeam(){
        
        StudentModel student = pickStudent();
        if (student != null){
            new SchoolController().assignStudentToTeam(student);
            view.displayMessage("Done");
        } else {
            view.displayMessage("Student does not exist...");
        }
    }

    private StudentModel pickStudent(){
        CreatableDAO dao = new StudentDAO();
        List<StudentModel> students = dao.getAllObjects();
        view.displayObjects(students);
        String chosenStudent = view.getUserInput("Choose student by ID: ");
        for (StudentModel student : students){
            if (chosenStudent.equals(String.valueOf(student.getId()))){
                return student;
            }
        }
        return null;
    }
}
