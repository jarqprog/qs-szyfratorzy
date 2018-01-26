package users;

import item.ArtifactDAO;
import item.ArtifactModel;
import item.QuestDAO;
import item.QuestModel;

import java.util.List;

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
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }
            view.clearScreen();
            switch(userChoice){
                case "1":
                   createStudent();
                   break;
                case "2":
                   createQuest();
                   break;
                case "3":
                   editQuest();
                   break;
                case "4":
                   createArtifact();
                   break;
                case "5":
                   editArtifact();
                   break;
                case "6":
                   markStudentQuest();
                   break;
                case "7":
                   displayStudentWallet();
                   break;
                case "8":
                   markStudentArtifacts();
                   break;
                case "0":
                   isDone = true;
                   break;
            }
            view.handlePause();
        }
    }

    public void createQuest() {
        String name = view.getUserInput("Enter quest name: ");
        String description = view.getUserInput("Enter quest description: ");
        int reward = view.getValue();
        QuestModel quest = new QuestModel(name, description, reward);
        view.displayMessage("You've created something new:\n" + quest);
    }

    public void editQuest() {

        List<QuestModel> quests = getQuests();
        view.displayElementsOfCollection(prepareQuestsToDisplay(quests));
        String input = view.getUserInput("Enter id of quest which you would like to edit: ");
        for(QuestModel quest : quests) {
            if(String.valueOf(quest.getId()).equals(input)) {
                handleQuestEditMenu(quest);
                break;
            }
        }

    }

    public void createStudent(){
        String firstName = view.getUserInput("Enter first name: ");
        String lastName = view.getUserInput("Enter last name: ");
        String password = view.getUserInput("Enter password: ");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        view.displayMessage("Student created successfully! \n" + newStudent.toString());
    }

    public void createArtifact(){
        String name = view.getUserInput("Enter artifact name: ");
        String description = view.getUserInput("Enter artifact description: ");
        int price = view.getValue();
        ArtifactModel artifact = new ArtifactModel(name, description, price);
        view.displayMessage("You've created something new:\n" + artifact);
        }

    public void editArtifact(){
        List<ArtifactModel> artifacts = getArtifacts();
        view.displayElementsOfCollection(prepareArtifactsToDisplay(artifacts));
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
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayArtifactMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }
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
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayQuestMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }
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

    public void markStudentQuest() {
        executeNotImplementedInfo();
    }

    public void displayStudentWallet() {
        executeNotImplementedInfo();
    }

    public void markStudentArtifacts() {
        executeNotImplementedInfo();
    }
}
