package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import model.StudentInventory;
import model.TeamInventory;
import model.Shop;
import view.ShopView;
import dao.ArtifactDAO;
import model.Artifact;
import model.Team;
import model.Student;

public class ShopController {

    Shop shop;
    ShopView view;
    ArtifactDAO artifactDao;
    Student student;

    public ShopController(Shop shop, Student student) {
        this.shop = shop;
        this.student = student;
        view = new ShopView();
        artifactDao = new ArtifactDAO();
        List<Artifact> artifacts = artifactDao.getAllObjects();
        shop.setStore(artifacts);
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
                        break;

                    } else {
                        view.displayMessageInNextLine("- this artifact is to expensive!");
                    }
                }
            }
            view.handlePause();
        }
        view.clearScreen();
        view.displayMessageInNextLine("Thank You for Your visit!");
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
                        break;
                    } else {
                        view.displayMessageInNextLine("- not enough coolcoins to buy this artifact!");
                    }
                }
            }
            view.handlePause();
        }
        view.clearScreen();
        view.displayMessageInNextLine("Thank You for Your visit!");
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
        List<Student> teamMates = student.getTeam().getStudents().stream() // teamMates pay
                                    .filter(s -> s.getId() != studentId)
                                    .collect(Collectors.toList());

        for (Student mate : teamMates) {
            view.displayMessage(String.valueOf(price));
            view.handlePause();
            mate.setWallet(mate.getWallet() - price);
        }
    }

    private List<Artifact> getArtifactsByType(char type) {

        List<Artifact> artifacts = new ArrayList<>();
        for(Artifact artifact : shop.getStore()){
            if(Objects.equals(artifact.getType(), type)) {
                artifacts.add(artifact);
            }
        }
        return artifacts;
    }
}

