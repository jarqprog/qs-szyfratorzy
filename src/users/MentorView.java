package users;

import application.*;
import java.util.Scanner;


public class MentorView extends AbstractView {

    public int getPrice() {
    Scanner scanner = new Scanner(System.in);
    boolean correctInput = false;
    int price = -1;
    while(! correctInput) {
        try {
            System.out.print("Enter a price of artifact: ");
            price = Integer.parseInt(scanner.nextLine());
            if(price > 0) {
            correctInput = true;
            }
            else {
                System.out.println("Price must be greater than 0!");
            }
        } catch(NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }
    return price;
    }

    public void displayMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] create student",
                            "[2] create quest",
                            "[3] edit quest",
                            "[4] create artifact",
                            "[5] edit artifact",
                            "[6] mark student quest",
                            "[7] display student wallet",
                            "[8] mark student artifact",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayArtifactMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] edit artifact name",
                            "[2] edit artifact type",
                            "[3] edit artifact description",
                            "[4] edit artifact price",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
