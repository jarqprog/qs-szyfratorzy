package application;

public class RootView extends AbstractView{

    public void displayIntro()
    {
        clearScreen();
        System.out.println("Welcome!!\n");
        System.out.println("[1] login");
        System.out.println("[0] exit\n");
    }

    public void displayOutro()
    {
        clearScreen();
        System.out.println(emptyLines);
        System.out.println("Bye!");
        System.out.println(emptyLines);
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
