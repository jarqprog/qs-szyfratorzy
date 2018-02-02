package shop;

import java.util.List;
import item.ArtifactDAO;
import item.ArtifactModel;
import users.StudentDAO;
import users.StudentModel;

public class ShopController {
    ShopModel shop;
    ShopView view;
    ArtifactDAO artifactDao;
    StudentDAO studentDAO;
    StudentModel student;
    ShopDAO shopDAO;

    public ShopController (ShopModel shop, StudentModel student) {
        this.shop = shop;
        this.student = student;
        view = new ShopView();
        artifactDao = new ArtifactDAO();
        List<ArtifactModel> artifacts = artifactDao.getManyObjects("SELECT * FROM artifacts;");
        shop.setStore(artifacts);
        }

    public void executeShoppingMenu() {
        boolean isDone = false;
        while(! isDone){
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
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
                    view.displayListOfArtifacts(shop.getStore());
                    break;
                case "2":
                    buyArtifact();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }

    private Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }

    public void buyArtifact() {
        view.displayListOfArtifacts(shop.getStore());
        int id = view.getUserChoice("Enter artifact id: ");
        for(ArtifactModel artifact : shop.getStore()) {
            if(id == artifact.getId()) {
                if(artifact.getPrice() <= student.getWallet()) {
                    student.getInventory().clear();
                    student.getInventory().add(artifact);
                    student.setWallet(student.getWallet()-artifact.getPrice());
                    view.displayMessage("You bought " + artifact.getName() + "!");
                    shopDAO = new ShopDAO();
                    shopDAO.saveInventory(student.getId(), student.getInventory());
                    shopDAO.saveStudentTransaction(student.getId(), artifact.getId());

                } else {
                    view.displayMessage("This artifact is to expensive!");
                }
            }
        }
        view.displayMessage("Bye");

    }
}
