package users;

import java.util.Scanner;

public class MentorView extends UsersView {

    public int getValue() {
    Scanner scanner = new Scanner(System.in);
    boolean correctInput = false;
    int value = -1;
    while(! correctInput) {
        try {
            System.out.print("Enter a value of item: ");
            value = Integer.parseInt(scanner.nextLine());
            if(value > 0) {
            correctInput = true;
            }
            else {
                System.out.println("Value must be greater than 0!");
            }
        } catch(NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }
    return value;
    }

    public void displayMenu() {
        String[] options = {"      *** Mentor's Menu ***     \n",
                            "[1] display profile",
                            "[2] create student",
                            "[3] assign student to group",
                            "[4] assign student to team",
                            "[5] create quest",
                            "[6] edit quest",
                            "[7] create artifact",
                            "[8] edit artifact",
                            "[9] mark student quest",
                            "[10] display student wallet",
                            "[11] mark student artifact",
                            "[12] display students from group",
                            "[13] create new team",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayArtifactMenu() {
        String[] options = {"      *** Artifacts Editor ***     ",
                            "[1] edit artifact name",
                            "[2] edit artifact type",
                            "[3] edit artifact description",
                            "[4] edit artifact price",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayQuestMenu() {
        String[] options = {"      *** Quest Editor ***     ",
                "[1] edit quest name",
                "[2] edit quest type",
                "[3] edit quest description",
                "[4] edit quest reward",
                "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
