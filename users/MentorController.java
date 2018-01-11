package users;

import item.*;
import java.util.Scanner;

public class MentorController {
    MentorView mentorView;
    MentorModel mentorModel;

    public MentorController(MentorModel mentorModel) 
    {
        mentorView = new MentorView();
    }

    public void handleMainMenu()
    {
        boolean exit = false;
        while(!exit){
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
            Boolean choiceIsReady = false;
            while(! choiceIsReady)
            {
                mentorView.clearScreen();
                mentorView.displayMenu();
                userChoice = mentorView.getUserInput("Select an option: ");
                choiceIsReady = checkIfElementInArray(correctChoices, userChoice);
            }
            mentorView.clearScreen();
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
                   exit = true;
                   break;
            }
            mentorView.handlePause();
        }
    }

    public Boolean checkIfElementInArray(String[] array, String element) 
    {
        for(String item : array)
        {
            if(item.equals(element))
            {
                return true;
            }
        }
        return false;
    }

    public void createQuest() {}

    public void editQuest() {}

    public void createStudent() 
    {
        String firstName = mentorView.getUserInput("Enter a first name:");
        String lastName = mentorView.getUserInput("Enter a last name:");
        String password = mentorView.getUserInput("Enter a password:");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        System.out.println(newStudent);

    }

    public void createArtefact() 
    {
        char itemType = mentorView.getUserInput("Enter an item type: ").charAt(0);
        String itemName = mentorView.getUserInput("Enter an item name:");
        String itemDescription = mentorView.getUserInput("Enter an item description:");
        int price = mentorView.getPrice();
        ArtefactModel artefact = new ArtefactModel(itemType, itemName, itemDescription, price);
        System.out.println(artefact);
    }

    public void editArtefact() {}

    public void markStudentQuest() {}

    public void displayStudentWallet() {}

    public void markStudentArtefacts() {}
}
