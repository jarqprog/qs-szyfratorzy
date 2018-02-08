package users;

import item.*;
import shop.*;


import java.util.List;
import java.util.Set;

public class StudentController extends UserController{
    StudentModel student;
    StudentView view;
    ShopDAO shopDAO;
    QuestDAO questDAO;
    QuestView questView;
    StudentsQuestsController studentsQuestsController;

    public StudentController(StudentModel studentModel){
        student = studentModel;
        view = new StudentView();
        updateStudentInventory();
    }

    private void showMyWallet(){
        view.displayMessage("Your wallet: " + String.valueOf(student.getWallet()));
    }

    private void showMyGroup(){
        view.displayMessage("Your Group:\n");
        view.displayObject(student.getGroup());
    }

    private void showMyTeam(){
        view.displayMessage("Your Team:\n");
        view.displayObject(student.getTeam());
    }

    private void showLevelOfExperience(){
        view.displayMessage("Your experience level: " + student.getExperienceLevel());
    }

    private void showMyInventory() { view.displayInventory(student.getInventory()); }

//    private void showTeamInventory() { view.displayInventory(student.getTeam().getInventory()); }

//    public void removeFromInventory(ArtifactModel artifact) {student.getInventory().remove(artifact); }

    private void updateStudentInventory() {
        student.getInventory().setStock();
        shopDAO = new ShopDAO();
        shopDAO.saveInventory(student.getId(), "students_artifacts", student.getInventory());
    }

//    private void updateTeamInventory() {
//        shopDAO = new ShopDAO();
//        List<String []> artifacts =  shopDAO.findTeamArtifacts(student.getTeam().getId());
//        student.getTeam().setInventory(shopDAO.loadInventory(artifacts));
//        shopDAO.saveInventory(student.getId(), student.getInventory());
//    }

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
                if(id == artifact.getId() && (Integer) student.getInventory().getStock().get(artifact) == 1) {
                    student.getInventory().removeArtifact(artifact);
                    view.displayMessage("Artifact used!");
                    shopDAO.deleteFromInventory(student.getId(), id);
                    break;
                }
                else if ((id == artifact.getId())) {
                    student.getInventory().decreaseQuantity(artifact);
                    shopDAO.saveInventory(student.getId(), "students_artifacts", student.getInventory());
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



    public void handleMainMenu() {

        boolean isDone = false;
        while(! isDone){

            String[] correctChoices = {"1", "2", "3", "4", "5","6", "0"};
            view.clearScreen();
            showProfile(student);
            view.displayMenu();
            String userChoice = view.getMenuChoice(correctChoices);


            switch(userChoice){

                case "1":
                    executeShopping();
                    updateStudentInventory();
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
//                case "5":
//                    showTeamInventory();
//                    break;
                case "6":
                    pickQuestToAchieve();
                    break;
                case "7":
                    showMyQuests();
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }
}
