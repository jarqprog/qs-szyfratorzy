package users;

import item.ArtifactDAO;
import item.ArtifactModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MentorController extends UserController{
    MentorView view;
    MentorModel mentor;
    MentorDAO dao;
    ArtifactDAO artifactDao;

    public MentorController(MentorModel mentorModel){
        view = new MentorView();
        mentor = mentorModel;
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

    public void createQuest() {}

    public void editQuest() {}

    public void createStudent(){
        String firstName = view.getUserInput("Enter a first name: ");
        String lastName = view.getUserInput("Enter a last name: ");
        String password = view.getUserInput("Enter a password: ");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        dao.saveModelToFile(newStudent);
        view.displayMessage("Student created successfully!");
    }

    public void createArtifact(){
        String itemName = view.getUserInput("Enter an item name: ");
        String itemDescription = view.getUserInput("Enter an item description: ");
        int price = view.getPrice();
        ArtifactModel artifact = new ArtifactModel(itemName, itemDescription, price);
        view.displayMessage("Artifact " + artifact + " created successfully!");
        artifactDao.saveArtifact(artifact);
    }

    public void editArtifact() {
        view.displayElementsOfCollection(
                        prepareArtifactsToDisplay(artifactDao.getArtifactsFromFile()));
        String input = view.getUserInput("Enter id of artifact which you would like to edit: ");
        for(ArtifactModel artifact : artifactDao.getArtifactsFromFile()) {
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
                   artifact.setItemName(view.getUserInput("Enter new artifact name: "));
                   artifactDao.saveArtifact(artifact);
                   break;
                case "2":
                   artifact.setItemType(view.getUserInput("Enter new artifact type: ").charAt(0));
                   artifactDao.saveArtifact(artifact);
                   break;
                case "3":
                   artifact.setItemDescription(view.getUserInput("Enter new artifact description: "));
                   artifactDao.saveArtifact(artifact);
                   break;
                case "4":
                   artifact.setPrice(view.getPrice());
                   artifactDao.saveArtifact(artifact);
                   break;
                case "0":
                   isDone = true;
                   break;
            }
        }
    }

    public void markStudentQuest() {}

    public void displayStudentWallet() {}

    public void markStudentArtifacts() {}

    public String[] prepareArtifactsToDisplay(List<ArtifactModel> artifacts){
        String[] artifactsToDisplay = new String[artifacts.size()];
        int index = 0;
        for(ArtifactModel artifact : artifacts){
            artifactsToDisplay[index] = artifact.toString();
            index ++;
        }
        return artifactsToDisplay;
    }
}
