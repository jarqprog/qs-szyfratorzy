package view;

import tools.DataTool;

import java.util.Scanner;
import java.io.IOException;
import java.util.List;

public abstract class AbstractView {

    private final String PROGRAM_NAME_AND_VERSION = " Quest Store v3.0 ";
    protected String emptyLines = "\n\n";
    protected String tab = "    ";
    protected String doubleTab = "      ";  // use while displaying collections
    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";

    public void displayMessage(String message) {
        System.out.println(tab + message);
    }
    public void displayMessageInNextLine(String message) {
        System.out.println("\n" + tab + message);
    }

    public <T> void displayObject(T object) {
        System.out.println(doubleTab + object.toString());
    }

    public <T> void displayObjects(List<T> objects) {
        for (T object : objects){
            displayObject(object);
        }
    }

    public void displayElementsOfCollection(String[] collection) {
        for(String element : collection){
        System.out.println(tab + element);
        }
    }

    public String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String delimiter = "\n";
        int minimumUserInputLength = 1;
        while(userInput.length() < minimumUserInputLength){
            System.out.print(emptyLines + tab + message);
            scanner.useDelimiter(delimiter);
            userInput = scanner.next();
        }
        return DataTool.removeWhitespacesFromString(userInput);
    }

    public String getNumberFromUser(String message) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String regex = ".*\\d+.*";
        int minimumUserInputLength = 1;
        while(! userInput.matches(regex) && userInput.length() < minimumUserInputLength){
            System.out.print(emptyLines + tab + message);
            scanner.useDelimiter("\n");
            userInput = scanner.next().trim();
            if(! userInput.matches(regex)){
                displayMessage("\n" + tab + "- Wrong input (number required)!");
            }
        }
        return userInput;
    }

    public int getIntegerFromUser(String message) {
        return Integer.parseInt(getNumberFromUser(emptyLines + tab + message));
    }

    public int getNotNegativeNumberFromUser(String message){
        int number = -1;
        while(number < 0) {
            String input = getNumberFromUser(emptyLines + tab + message);
            try {
                number = Integer.parseInt(input);
                if (number < 0) {
                    displayMessage("\n" + tab + "- number shouldn't be negative!");
                }
            } catch (NumberFormatException e){
                displayMessage("\n" + tab + "- have You type an integer number?");
                number = -1;
            }
        }
        return number;
    }

    public void clearScreen() {
        try{
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print(ANSI_CLS + ANSI_HOME);
                System.out.flush();
                System.out.println();
            }
            drawHeading();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void drawNextLine() {
        System.out.println();
    }

    public void handlePause() {
        System.out.print(emptyLines + tab + "Press enter to continue.. ");
        try {
            System.in.read();
        }catch(IOException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public String getMenuChoice(String[] correctChoices) {
        String menuChoice = "";
        Boolean isChoiceReady = false;
        while (!isChoiceReady) {
            menuChoice = getUserInput(emptyLines + doubleTab + "Select an option: ");
            isChoiceReady = DataTool.checkIfElementInArray(correctChoices, menuChoice);
        }
        return menuChoice;
    }

    protected void handleDelay(int pauseInMiliseconds){
        try{
            Thread.sleep(pauseInMiliseconds);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    private void drawHeading() {
        int terminalWidth = 140;
        int additionalHeadingWidth = (terminalWidth - PROGRAM_NAME_AND_VERSION.length()) / 2;
        String additionalHeading = DataTool.getMultipliedString("=", additionalHeadingWidth);
        String heading = DataTool.getMultipliedString("=", terminalWidth);
        System.out.println(emptyLines + tab + heading);
        System.out.println(tab + additionalHeading + PROGRAM_NAME_AND_VERSION + additionalHeading);
        System.out.println(tab + heading);
        System.out.println(emptyLines);
    }
}
