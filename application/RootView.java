package application;

public class RootView extends AbstractView{

    public void displayIntro()
    {
        clearScreen();
        System.out.println(emptyLines);
        System.out.println("Welcome!!");
        System.out.println(emptyLines);
        handlePause();
    }

    public void displayOutro()
    {
        clearScreen();
        System.out.println(emptyLines);
        System.out.println("Bye!");
        System.out.println(emptyLines);
        handlePause();
        clearScreen();
    }

    public String displayLoginScreen(String message)
    {
        return getUserInput(message);
    }

    public void displayLogoutScreen()
    {
        displayMessage("Logout.... ;( ");
        handlePause();
    }

}
