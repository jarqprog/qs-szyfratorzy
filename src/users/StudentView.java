package users;

import application.AbstractView;
import item.ArtifactModel;

import java.util.List;

public class StudentView extends AbstractView{

    public void displayWallet(int wallet){
        displayMessage("Your wallet:");
        displayMessage(Integer.toString(wallet));
    }

    public void displayLevelOfExperience(int experience){
        displayMessage("Your experience:");
        displayMessage(Integer.toString(experience));
    }

    public void displayInventory(List<ArtifactModel> inventory) {
        if(inventory.isEmpty()) {
            displayMessage("Your inventory is empty");
        } else {
            displayMessage("Your Inventory: \n");
            for(ArtifactModel artifact : inventory) {
            System.out.println(artifact);
            }
        }
    }

    public void displayMenu() {
        String[] options = {"      *** Student's Menu ***     ",
                            "[1] display wallet",
                            "[2] display level of experience",
                            "[3] shop online",
                            "[4] display inventory",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
