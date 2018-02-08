package users;

import item.*;
import shop.*;


import java.util.List;
import java.util.Set;

public class StudentController extends UserController{
    private StudentModel student;
    private StudentView view;

    public StudentController(StudentModel studentModel){
        student = studentModel;
        view = new StudentView();
    }

    private void showMyInventory() { view.displayInventory(student.getInventory()); }

    private void showTeamInventory() { view.displayInventory(student.getTeam().getInventory()); }

    private void executeShopping() {
        ShopModel shop = new ShopModel();
        ShopController controller = new ShopController(shop, student);
        controller.executeShoppingMenu();
        }

    private void useArtifacts() {
        showMyInventory();
        if(student.getInventory().getStock().isEmpty()){
            view.displayMessage("Sorry, You have nothing to use!");
            view.handlePause();
        } else {
            int id = view.getNumber("Enter artifact id: ");
            Set<ArtifactModel> artifacts = student.getInventory().getStock().keySet();
            for(ArtifactModel artifact : artifacts) {
                if(id == artifact.getId() && student.getInventory().getStock().get(artifact) == 1) {
                    student.getInventory().removeArtifact(artifact);
                    view.displayMessage("Artifact used!");
                    break;
                }
                else if ((id == artifact.getId())) {
                    student.getInventory().decreaseQuantity(artifact);
                    view.displayMessage("Artifact used!");
                }
            }
        }
    }

    private void showStudentsFromMyTeam() {
        List<StudentModel> students = student.getTeam().getStudents();
        view.displayMessage("Your teammates:\n");
        view.displayUsersWithDetails(students);
    }
    private void pickQuestToAchieve(){
        StudentsQuestsController studentQuestsCtrl = new StudentsQuestsController();
        StudentsQuestsModel studentsQuests = student.getStudentsQuests();
        studentQuestsCtrl.addQuestToStudentsQuests(studentsQuests);
    }
    private void showMyQuests() {

    }

    private void useTeamArtifacts() {
        showTeamInventory();
        if(student.getTeam().getInventory().getStock().isEmpty()){
            view.displayMessage("Sorry, You have nothing to use!");
            view.handlePause();
        } else {
            int id = view.getNumber("Enter artifact id: ");
            Set<ArtifactModel> artifacts = student.getTeam().getInventory().getStock().keySet();
            for(ArtifactModel artifact : artifacts) {
                if(id == artifact.getId() && student.getTeam().getInventory().getStock().get(artifact) == 1) {
                    student.getTeam().getInventory().removeArtifact(artifact);
                    view.displayMessage("Artifact used!");
                    break;
                }
                else if ((id == artifact.getId())) {
                    student.getTeam().getInventory().decreaseQuantity(artifact);
                    view.displayMessage("Artifact used!");
                }
            }
        }
    }

    public void handleMainMenu() {

        boolean isDone = false;
        while(! isDone){

            String[] correctChoices = {"1", "2", "3", "4", "5","6", "7", "8", "0"};
            view.clearScreen();
            showProfile(student);
            view.displayMenu();
            String userChoice = view.getMenuChoice(correctChoices);


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
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }
}
