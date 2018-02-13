package view;

import tools.DataTool;

import java.util.List;
import java.io.Console;

public class RootView extends AbstractView {

    public void displayIntro(List<String> dataToDisplay){
        clearScreen();
        for(String data : dataToDisplay){
            System.out.println(data);
        }
    }

    public void displayOutro(List<String> dataToDisplay){
        clearScreen();
        for(String data : dataToDisplay){
            System.out.println(data);
        }
        handlePause();
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

    public void displayAuthors() {
        clearScreen();
        String beam = DataTool.getMultipliedString("*", 70);
        System.out.println(emptyLines);
        System.out.println(emptyLines + doubleTab + doubleTab + beam);
        System.out.println(emptyLines);
        System.out.println(doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + "    Quest Store");
        System.out.println(doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + "    by Szyfratorzy");
        System.out.println(doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + doubleTab + "    v 2.0");
        System.out.println(emptyLines);
        System.out.println(emptyLines + doubleTab + doubleTab + beam);
        System.out.println(emptyLines);
        handlePause();
    }

    public void displayLoadingStars() {
        int pauseInMiliseconds = 100;
        int loops = 20;
        for (int i=0; i<loops; i++) {
            System.out.println();
            System.out.print(tab);
            System.out.print(DataTool.getMultipliedString(" ", i) + "* .");
            handleDelay(pauseInMiliseconds);
        }
        System.out.println(emptyLines);
    }
}
