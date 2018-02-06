package users;

import item.ArtifactModel;

import java.util.List;

public class StudentView extends UsersView {

    public void displayInventory(List<ArtifactModel> inventory) {
        if (inventory.isEmpty()) {
            displayMessage("Your inventory is empty");
        } else {
            displayMessage("Your Inventory: \n");
            for (ArtifactModel artifact : inventory) {
                System.out.println(artifact);
            }
        }
    }

    public void displayMenu() {
        String[] options = {"      *** Student's Menu ***     \n",
                "[1] Enter to online shop",
                "[2] display inventory",
                "[3] use artifact",
                "[4] display teammates",
                "[5] display team inventory",
                "[0] exit"};

        for (String element : options) {
            System.out.printf("\t%s\n", element);
        }
        System.out.println("\n");
    }
  
}

