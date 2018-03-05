package view;

import model.StudentInventory;
import model.TeamInventory;

public class StudentView extends UsersView {

    public void displayInventory(StudentInventory inventory) {
        if (inventory.getStock().isEmpty()) {
            displayMessage("- Your inventory is empty");
        }
        else {
            displayMessage("Your Inventory: \n");
            displayInventoryWithDetails(inventory);
        }
    }

    public void displayInventory(TeamInventory inventory) {
        if (inventory.getStock().isEmpty()) {
            displayMessage("- Your team's inventory is empty");
        }
        else {
            displayMessage("Your team's inventory: \n");
            displayInventoryWithDetails(inventory);
        }
    }

    public void displayMenu() {
        String[] options = {"\n\n      *** Student's Menu ***\n",
                "[1] Enter to online shop",
                "[2] display inventory",
                "[3] use artifact",
                "[4] display teammates",
                "[5] display team inventory",
                "[6] use team artifact",
                "[7] achieve quest",
                "[8] display my quests",
                "[9] display my attendance",
                "[0] exit"};

        displayElementsOfCollection(options);
    }
  
}

