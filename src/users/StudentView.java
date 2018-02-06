package users;

import item.ArtifactModel;

import java.util.List;

public class StudentView extends UsersView {

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
        String[] options = {"      *** Student's Menu ***     \n",
                            "[1] display profile",
                            "[2] display wallet",
                            "[3] display team inventory",
                            "[4] shop online",
                            "[5] display inventory",
                            "[6] display group",
                            "[7] display team",
                            "[8] use artifact",
                            "[9] display teammates",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
