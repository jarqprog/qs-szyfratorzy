package users;

import java.util.List;

import school.ExperienceLevelsController;
import school.GroupModel;
import school.SchoolController;

public class AdminController extends UserController{

    private AdminModel admin;
    private AdminView view;

    public AdminController(AdminModel admin){
        this.admin = admin;
        this.view = new AdminView();
    }

    public void handleMainMenu(){

        boolean isDone = false;
        while(! isDone) {
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "0"};
            view.clearScreen();
            view.displayMenu();
            String userChoice = view.getMenuChoice(correctChoices);
            view.clearScreen();

            switch(userChoice) {
                case "1":
                    showProfile(admin);
                    break;
                case "2":
                    createMentor();
                    break;
                case "3":
                    editMentor();
                    break;
                case "4":
                    displayMentorProfile();
                    break;
                case "5":
                    executeNotImplementedInfo();
                    break;
                case "6":
                    createGroup();
                    break;
                case "7":
                    runExpLevelManager();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }

    private void createMentor(){
        String firstName = view.getUserInput("Enter first name: ");
        String lastName = view.getUserInput("Enter last name: ");
        String password = view.getUserInput("Enter password: ");
        MentorModel mentor = new MentorModel(firstName, lastName, password);
        view.clearScreen();
        view.displayMessage("Mentor created: " + mentor.toString());
    }

    private void editMentor() {
        List<MentorModel> mentors = SchoolController.getAllMentors();
        view.displayUsers(mentors);
        String id = view.getUserInput("Enter ID of mentor: ");
        for (MentorModel mentor : mentors) {
            if (id.equals(Integer.toString(mentor.getId()))) {
                boolean isFinished = false;
                while(! isFinished) {
                    view.clearScreen();
                    view.displayEditMenu();
                    String userChoice = view.getUserInput("Select an option: ");
                    view.clearScreen();
                    switch(userChoice) {
                        case "1" :
                            String firstName = view.getUserInput("Enter first name: ");
                            mentor.setFirstName(firstName);
                            break;
                        case "2" :
                            String lastName = view.getUserInput("Enter last name: ");
                            mentor.setLastName(lastName);
                            break;
                        case "3" :
                            String password = view.getUserInput("Enter password: ");
                            mentor.setPassword(password);
                            break;
                        case "4" :
                            String email = view.getUserInput("Enter email: ");
                            mentor.setEmail(email);
                            break;
                        case "5" :
                            SchoolController.assignMentorToGroup(mentor);
                            break;
                        case "0":
                            MentorDAO dao = new MentorDAO();
                            dao.saveObject(mentor);
                            isFinished = true;
                            break;
                    }
                    if(! isFinished){
                        view.clearScreen();
                        view.displayMessage("Mentor`s data:\n");
                        view.displayUserWithDetails(mentor);
                        view.handlePause();
                    }
                }
            }
        }
    }

    private void displayMentorProfile() {
        List<MentorModel> mentors = SchoolController.getAllMentors();
        view.displayMessage("Mentors:\n");
        view.displayUsers(mentors);
        String id = view.getUserInput("Select mentor by id: ");
        for (MentorModel mentor : mentors) {
            if (id.equals(Integer.toString(mentor.getId()))) {
                view.clearScreen();
                view.displayMessage("Mentor's profile:\n");
                view.displayUserWithDetails(mentor);
            }
        }
    }

    private void displayStudentsByMentor() {

    }

    private void createGroup(){
        String groupName = view.getUserInput("Enter group name: ");
        GroupModel group = new GroupModel(groupName);
        view.clearScreen();
        view.displayMessage("Group created: " + group);
    }

    private void runExpLevelManager(){
        ExperienceLevelsController controller = new ExperienceLevelsController();
        controller.manageExperienceLevels();
    }
}
