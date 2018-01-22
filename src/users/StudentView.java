package users;

import application.AbstractView;

public class StudentView extends AbstractView{

    public void displayWallet(int wallet){
        displayMessage("Your wallet:");
        displayMessage(Integer.toString(wallet));
    }

    public void displayLevelOfExperience(int experience){
        displayMessage("Your experience:");
        displayMessage(Integer.toString(experience));
    }

    public void displayMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] display wallet",
                            "[2] display level of experience",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
