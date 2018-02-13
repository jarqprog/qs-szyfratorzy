package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            id = view.getIntegerFromUser("Enter artifact id or 0 to exit shop: ");
            for (Artifact artifact : shop.getStore()) {
                if (id == artifact.getId() && Objects.equals(artifact.getType(), 'B')) {
                    if (artifact.getPrice() <= student.getWallet()) {
                        finalizeTransaction(artifact);
                        pay(artifact);
                        view.displayMessage("You've bought " + artifact.getName() + "!\n");
                        view.displayObject(artifact);

                    } else {
                        view.displayMessage("- this artifact is to expensive!");
                    }
                }
            }
        }
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
            id = view.getIntegerFromUser("Enter artifact id or 0 to exit shop:: ");
            for (Artifact artifact : shop.getStore()) {
                if (id == artifact.getId() && Objects.equals(artifact.getType(), 'M')) {
                    if (checkTeamResources(artifact, student.getTeam())) {
                        chargeTeamMembers(artifact, student.getTeam());
                        finalizeTeamTransaction(artifact);
                        view.displayMessage("- You've bought " + artifact.getName() + "!\n");
                        view.displayObject(artifact);
                    } else {
                        view.displayMessageInNextLine("- not enough coolcoins to buy this artifact!");
                    }
                }
            }
        }
        view.displayMessageInNextLine("Thank You for Your visit!");
    }

    private boolean checkTeamResources(Artifact artifact, Team team) {

        int pricePerTeamMember = artifact.getPrice() / team.getStudents().size();
        for (Student student : team.getStudents()) {
            if (student.getWallet() < pricePerTeamMember) {
                return false;
            }
        }
        return true;
    }

    private void chargeTeamMembers(Artifact artifact, Team team) {

        for (Student student : team.getStudents()) {
            student.setWallet(student.getWallet() - (artifact.getPrice() / team.getStudents().size()));
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

