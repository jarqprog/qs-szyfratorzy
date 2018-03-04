package controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dao.DaoFactory;
import model.StudentInventory;
import model.TeamInventory;
import model.Shop;
import view.ShopView;
import dao.ArtifactDAO;
import model.Artifact;
import model.Student;

public class ShopController {

    private Shop shop;
    private ShopView view;
    private Student student;

    public ShopController(Shop shop, Student student) {
        this.shop = shop;
        this.student = student;
        view = new ShopView();
        shop.setStore(DaoFactory.getByType(ArtifactDAO.class).getAllModels());
    }

    public void executeShoppingMenu() {
        boolean isDone = false;
        while (!isDone) {
            String userChoice;
            String[] correctChoices = {"1", "2", "3", "0"};
            view.clearScreen();
            view.displayMenu();
            userChoice = view.getMenuChoice(correctChoices);

            view.clearScreen();
            switch (userChoice) {
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
            if (isDone) {
                view.clearScreen();
                view.displayMessageInNextLine("Thank You for Your visit!");
            }
        }
    }

    private void buyArtifact() {
        int id = -1;
        while (id != 0) {
            view.clearScreen();
            view.displayListOfArtifacts(getArtifactsByType('B'));
            view.displayStudentWallet(student.getWallet());
            id = view.getIntegerFromUser("Enter artifact id or 0 to exit shop: ");
            for (Artifact artifact : shop.getStore()) {
                if (id == artifact.getId() && Objects.equals(artifact.getType(), 'B')) {
                    if (artifact.getPrice() <= student.getWallet()) {
                        finalizeTransaction(artifact);
                        pay(artifact);
                        view.displayMessageInNextLine("You've bought " + artifact.getName() + "!\n");
                        view.displayObject(artifact);
                        view.handlePause();
                        break;

                    } else {
                        view.displayMessageInNextLine("- this artifact is to expensive!");
                        view.handlePause();
                    }
                }
            }
        }
    }

    private void finalizeTransaction(Artifact artifact) {
        StudentInventory inventory = student.getInventory();
        if(inventory.containsItem(artifact)) {
            inventory.modifyQuantity(artifact);
        } else {
            inventory.addItem(artifact);
        }
    }

    private void finalizeTeamTransaction(Artifact artifact) {
        TeamInventory inventory = student.getTeam().getInventory();
        if(inventory.containsItem(artifact)) {
            inventory.modifyQuantity(artifact);
        } else {
            inventory.addItem(artifact);
        }
    }

    private void pay(Artifact artifact) {
        student.setWallet(student.getWallet() - artifact.getPrice());
    }

    private void buyArtifactForTeam() {
        int id = -1;
        while (id != 0) {
            view.clearScreen();
            view.displayListOfArtifacts(getArtifactsByType('M'));
            view.displayStudentWallet(student.getWallet());
            id = view.getIntegerFromUser("Enter artifact id or 0 to exit shop: ");
            for (Artifact artifact : shop.getStore()) {
                if (id == artifact.getId() && Objects.equals(artifact.getType(), 'M')) {
                    if (checkTeamResources(artifact)) {
                        chargeTeamMembers(artifact);
                        finalizeTeamTransaction(artifact);
                        view.displayMessageInNextLine("- You've bought " + artifact.getName() + "!\n");
                        view.displayObject(artifact);
                        view.handlePause();
                        break;
                    } else {
                        view.displayMessageInNextLine("- not enough CoolCoins to buy this artifact!");
                        view.handlePause();
                    }
                }
            }
        }
    }

    private boolean checkTeamResources(Artifact artifact) {
        List<Student> teamMates = student.getTeam().getStudents();
        int pricePerTeamMember = artifact.getPrice() / teamMates.size();
        for (Student student : teamMates) {
            if (student.getWallet() < pricePerTeamMember) {
                return false;
            }
        }
        return true;
    }

    private void chargeTeamMembers(Artifact artifact) {
        List<Student> allTeam = student.getTeam().getStudents();
        int price = artifact.getPrice() / allTeam.size();
        student.setWallet(student.getWallet() - price); // student pays his part
        int studentId = student.getId();
        allTeam.stream() // teamMates pay
                .filter(s -> s.getId() != studentId)
                .forEach(s -> s.setWallet(s.getWallet() - price));
    }

    private List<Artifact> getArtifactsByType(char type) {
        return shop.getStore().stream().filter(a -> Objects.equals(a.getType(), type))
                                .collect(Collectors.toList());
    }
}

