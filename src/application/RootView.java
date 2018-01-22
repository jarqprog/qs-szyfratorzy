package application;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class RootView extends AbstractView{

    public void displayIntro(List<String> dataToDisplay){
        clearScreen();
        for(String data : dataToDisplay){
            System.out.println(data);
        }
        System.out.println("\n\n[1] login");
        System.out.println("[0] exit\n");
    }

    public void displayOutro(List<String> dataToDisplay){
        clearScreen();
        for(String data : dataToDisplay){
            System.out.println(data);
        }
        handlePause();
    }

    public String displayLoginScreen(String message){
        return getUserInput(message);
    }

    public void displayLogoutScreen(){
        displayMessage("Logout.... ;( ");
        handlePause();
    }
}
