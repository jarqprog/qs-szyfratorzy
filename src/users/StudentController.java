package users;

import item.ArtifactModel;
import shop.*;

import java.util.List;

public class StudentController extends UserController{
    StudentModel student;
    StudentView view;
    ShopDAO shopDAO;

    public StudentController(StudentModel studentModel){
        student = studentModel;
        view = new StudentView();
        updateInventory();
    }



    public void showMyWallet(){
        view.displayWallet(student.getWallet());
    }

    public void showMyGroupName(){
        view.displayMessage(student.getGroup().getName());
    }

    public void showMyTeamName(){
        view.displayMessage(student.getTeam().getName());
    }

    public void showLevelOfExperience(){
        view.displayLevelOfExperience(student.getExperience());
    }

    public void showMyInventory() { view.displayInventory(student.getInventory()); }

    public void removeFromInventory(ArtifactModel artifact) {student.getInventory().remove(artifact); }

    public void updateInventory() {
        shopDAO = new ShopDAO();
        List<String []> artifacts =  shopDAO.findStudentArtifacts(student.getId());
        student.setInventory(shopDAO.loadInventory(artifacts));
        shopDAO.saveInventory(student.getId(), student.getInventory());
    }

    public void executeShopping() {
        ShopModel shop = new ShopModel();
        ShopController controller = new ShopController(shop, student);
        controller.executeShoppingMenu();
        }

    public void useArtifacts() {
        showMyInventory();
        if(student.getInventory().isEmpty()){
            view.displayMessage("Sorry, You have nothing to use!");
            view.handlePause();
        } else {
            int id = view.getUserChoice("Enter artifact id: ");
            for(ArtifactModel artifact : student.getInventory()) {
                if(id == artifact.getId()) {
                    student.getInventory().remove(artifact);
                    view.displayMessage("Artifact used!");
                    shopDAO.deleteFromInventory(student.getId(), id);
                    updateInventory();
                    break;
                }
            }
        }
    }

    public void handleMainMenu(){
        boolean isDone = false;
        while(! isDone){

            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "0"};
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
                    updateInventory();
                    break;
                case "4":
                    showMyInventory();
                    break;
                case "5":
                    showMyGroupName();
                    break;
                case "6":
                    showMyTeamName();
                    break;
                case "7":
                    useArtifacts();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }
}
