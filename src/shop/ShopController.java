package shop;

import java.util.List;
import java.util.Objects;

import application.DataTool;
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
            String[] correctChoices = {"1", "2", "3", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = DataTool.checkIfElementInArray(correctChoices, userChoice);
            }
            view.clearScreen();
            switch(userChoice){
                case "1":
                    view.displayListOfArtifacts(shop.getStore());
                    view.handlePause();
                    break;
                case "2":
                    buyArtifact();
                    view.handlePause();
                    break;
                case "3":
                    buyArtifactForTeam();
                    view.handlePause();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
        }
    }


    public void buyArtifact() {
        view.displayListOfArtifacts(shop.getStore(), 'B');
        int id = view.getUserChoice("Enter artifact id: ");
        for(ArtifactModel artifact : shop.getStore()) {
            if(id == artifact.getId() && Objects.equals(artifact.getType(), 'B')) {
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

    public void buyArtifactForTeam() {
        view.displayListOfArtifacts(shop.getStore(), 'M');
        int id = view.getUserChoice("Enter artifact id: ");
        for(ArtifactModel artifact : shop.getStore()) {
            if(id == artifact.getId() && Objects.equals(artifact.getType(), 'M')) {
                if(checkTeamResources()) {
                    chargeTeamMembers()
                    student.getTeam().getInventory().add(artifact);
//                    save

                }
                else {
                    view.displayMessage("Not enough codecool coins to buy this artifact!");
                }
            }
        }
    }

    public boolean checkTeamResources() { return true; }
}
