package users;

import shop.*;

public class StudentController extends UserController{
    StudentModel student;
    StudentView view;

    public StudentController(StudentModel studentModel){
        student = studentModel;
        view = new StudentView();
    }

    public void showMyWallet(){
        view.displayWallet(student.getWallet());
    }

    public void showLevelOfExperience(){
        view.displayLevelOfExperience(student.getExperience());
    }

    public void showMyInventory() { view.displayInventory(student.getInventory()); }

    public void executeShopping() {
        Shop shop = new Shop();
        ShopController controller = new ShopController(shop);
        controller.executeShoppingMenu();
        }

    public void handleMainMenu(){
        boolean isDone = false;
        while(! isDone){

            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){

                view.clearScreen();
                view.displayMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }

            view.clearScreen();
            switch(userChoice){
                case "1":
                    showMyWallet();
                    break;
                case "2":
                    showLevelOfExperience();
                    break;
                case "3":
                    executeShopping();
                    break;
                case "4":
                    showMyInventory();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }
}
