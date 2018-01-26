package users;

import item.ArtifactDAO;
import item.ArtifactModel;
import java.util.List;

public class MentorController extends UserController{
    MentorView view;
    MentorModel mentor;
    MentorDAO dao;
    ArtifactDAO artifactDao;

    public MentorController(MentorModel mentorModel){
        mentor = mentorModel;
        view = new MentorView();
        dao = new MentorDAO();
        artifactDao = new ArtifactDAO();
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
        executeNotImplementedInfo();
    }

    public void editQuest() {
        executeNotImplementedInfo();
    }

    public void createStudent(){
        String firstName = view.getUserInput("Enter a first name: ");
        String lastName = view.getUserInput("Enter a last name: ");
        String password = view.getUserInput("Enter a password: ");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        view.displayMessage("Student created successfully! \n" + newStudent.toString());
    }

    public void createArtifact(){
        String name = view.getUserInput("Enter an artifact name: ");
        String description = view.getUserInput("Enter an item description: ");
        int price = view.getPrice();
        ArtifactModel artifact = new ArtifactModel(name, description, price);
        view.displayMessage("Artifact " + artifact + " created successfully!");
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
                    artifact.setPrice(view.getPrice());
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
