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
            System.out.println("Enter a price of artifact: ");
            price = Integer.parseInt(scanner.nextLine());
            if(price > 0) {
            correctInput = true;
            }
            else {
                System.out.println("Price must be greater than 0!");
            }
        } catch(Exception e) {
            System.out.println("Wrong input!");
        }
    }
    return price;
    }
}
