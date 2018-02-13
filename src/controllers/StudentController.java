package controllers;

import model.*;
import view.StudentView;

import java.util.List;
import java.util.Set;

public class StudentController extends UserController{
    private Student student;
    private StudentView view;

    public StudentController(Student student){
        this.student = student;
        view = new StudentView();
    }

    public void handleMainMenu() {

        boolean isDone = false;
        while(! isDone){

            String[] correctChoices = {"1", "2", "3", "4", "5","6", "7", "8", "9", "0"};
            view.clearScreen();
            showProfile(student);
            view.displayMenu();
            String userChoice = view.getMenuChoice(correctChoices);
            view.clearScreen();
            switch(userChoice){

                case "1":
                    executeShopping();
                    break;
                case "2":
                    showMyInventory();
                    break;
                case "3":
                    useArtifacts();
                    break;
                case "4":
                    showStudentsFromMyTeam();
                    break;
                case "5":
                    showTeamInventory();
                    break;
                case "6":
                    useTeamArtifacts();
                    break;
                case "7":
                    pickQuestToAchieve();
                    break;
                case "8":
                    showMyQuests();
                    break;
                case "9":
                    showMyAttendance();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }

    private void showMyInventory() { view.displayInventory(student.getInventory()); }

    private void showTeamInventory() { view.displayInventory(student.getTeam().getInventory()); }

    private void executeShopping() {
        Shop shop = new Shop();
        ShopController controller = new ShopController(shop, student);
        controller.executeShoppingMenu();
        }

    private void useArtifacts() {
        showMyInventory();
        if(student.getInventory().getStock().isEmpty()){
            view.displayMessageInNextLine("- sorry, You have nothing to use!");
        } else {
            int id = view.getIntegerFromUser("Enter artifact id: ");
            StudentInventory inventory = student.getInventory();
            Set<Artifact> artifacts = inventory.getStock().keySet();
            for(Artifact artifact : artifacts) {
                if(id == artifact.getId() && inventory.getStock().get(artifact) == 1) {
                    inventory.removeArtifact(artifact);
                    view.displayMessageInNextLine("- artifact used!");
                }
                else if (id == artifact.getId() && id == artifact.getId()) {
                    inventory.decreaseQuantity(artifact);
                    view.displayMessageInNextLine("- artifact used!");
                }
            }
        }
    }

    private void showStudentsFromMyTeam() {
        List<Student> students = student.getTeam().getStudents();
        view.displayMessageInNextLine("Your teammates:\n");
        view.displayManyUsersWithDetails(students);
    }
    private void pickQuestToAchieve(){
        StudentsQuestsController studentQuestsCtrl = new StudentsQuestsController();
        studentQuestsCtrl.runQuestMenu(student);
    }
    private void showMyQuests() {
        if(student.getStudentsQuests().isEmpty()) {
            view.displayMessageInNextLine("- sorry, there is nothing to show!");
        } else {
        view.displayObject(student.getStudentsQuests());
        }
    }

    private void showMyAttendance() {
        view.displayMessageInNextLine("Your attendance:\n");
        view.displayMessage(student.getAttendance().getFullDataToString());
    }

    private void useTeamArtifacts() {
        showTeamInventory();
        if(student.getTeam().getInventory().getStock().isEmpty()){
            view.displayMessageInNextLine("- sorry, You have nothing to use!");
        } else {
            int id = view.getIntegerFromUser("Enter artifact id: ");
            TeamInventory inventory = student.getTeam().getInventory();
            Set<Artifact> artifacts = inventory.getStock().keySet();
            for(Artifact artifact : artifacts) {
                if(id == artifact.getId() && inventory.getStock().get(artifact) == 1) {
                    inventory.removeArtifact(artifact);
                    view.displayMessageInNextLine("- artifact used!");
                    break;
                }
                else if ((id == artifact.getId())) {
                    inventory.decreaseQuantity(artifact);
                    view.displayMessageInNextLine("- artifact used!");
                }
            }
        }
    }
}
