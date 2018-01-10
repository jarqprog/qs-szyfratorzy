package users;

import item.*;
import java.util.Scanner;

public class MentorController {
    MentorView mentorView;

    public static void main(String[] args) {
        MentorController a = new MentorController();
        a.createStudent();
        a.createArtifact();
    }

    public MentorController() {
        mentorView = new MentorView();

    }

    public void createStudent() {
        String firstName = mentorView.getUserInput("Enter a first name:");
        String lastName = mentorView.getUserInput("Enter a last name:");
        String password = mentorView.getUserInput("Enter a password:");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        System.out.println(newStudent);

    }

    public void createArtifact() {
        char itemType = mentorView.getUserInput("Enter an item type: ").charAt(0);
        String itemName = mentorView.getUserInput("Enter an item name:");
        String itemDescription = mentorView.getUserInput("Enter an item description:");
        int price = mentorView.getPrice();
        ArtefactModel artefact = new ArtefactModel(itemType, itemName, itemDescription, price);
        System.out.println(artefact);
    }
}
