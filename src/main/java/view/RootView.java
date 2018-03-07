package view;

import tools.DataTool;

import java.util.List;
import java.io.Console;

public class RootView extends AbstractView {

    public void displayCollectionData(List<String> dataToDisplay){
        clearScreen();
        for(String data : dataToDisplay){
            System.out.println(data);
        }
    }

    public void displayLoginScreen(){
        System.out.println(emptyLines);
        System.out.println(doubleTab + "[1] enter Quest Store");
        System.out.println(doubleTab + "[2] about..");
        System.out.println(doubleTab + "[0] exit\n");
        System.out.println(emptyLines);
    }

    public String getLogin(){
        return getUserInput(emptyLines + doubleTab + "Enter Your login: ");
    }

    public String getPassword() {
        Console console = System.console();
        System.out.print(doubleTab + "Please enter your password: ");
        char[] password = console.readPassword();
        return String.valueOf(password);
    }

    public void displayLoadingStars() {
        int pauseInMiliseconds = 100;
        int loops = 20;
        for (int i=0; i<loops; i++) {
            System.out.println();
            System.out.print(tab);
            System.out.print("." + DataTool.getMultipliedString(" ", i) + "*" + DataTool.getMultipliedString(" ", i) + "." +
            DataTool.getMultipliedString(" ", i) + "*" + DataTool.getMultipliedString(" ", i) + "." +
            DataTool.getMultipliedString(" ", i) + "*" + DataTool.getMultipliedString(" ", i) + ".");
            handleDelay(pauseInMiliseconds);
        }
        System.out.println(emptyLines);
        displayMessage("System is ready to use...");
        handlePause();
    }
}
