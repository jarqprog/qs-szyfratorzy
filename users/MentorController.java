package users;

import item.*;
import java.util.Scanner;

public class MentorController extends UserController{
    MentorView view;
    MentorModel mentor;
    MentorDAO dao;

    public MentorController(MentorModel mentorModel){
        view = new MentorView();
        mentor = mentorModel;
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
                   createArtefact();
                   break;
                case "5":
                   editArtefact();
                   break;
                case "6":
                   markStudentQuest();
                   break;
                case "7":
                   displayStudentWallet();
                   break;
                case "8":
                   markStudentArtefacts();
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
        String firstName = view.getUserInput("Enter a first name:");
        String lastName = view.getUserInput("Enter a last name:");
        String password = view.getUserInput("Enter a password:");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        dao.saveModelToFile(newStudent);
    }

    public void createArtefact(){
        char itemType = view.getUserInput("Enter an item type: ").charAt(0);
        String itemName = view.getUserInput("Enter an item name:");
        String itemDescription = view.getUserInput("Enter an item description:");
        int price = view.getPrice();
        ArtefactModel artefact = new ArtefactModel(itemType, itemName, itemDescription, price);
        System.out.println(artefact);
    }

    public void editArtefact() {}

    public void markStudentQuest() {}

    public void displayStudentWallet() {}

    public void markStudentArtefacts() {}
}
