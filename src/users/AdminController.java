package users;

import java.util.List;

import school.ExperienceLevels;
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
        while(! isDone){
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }
            view.clearScreen();

            switch(userChoice) {
                case "1":
                   createMentor();
                   break;
                case "2":
                   editMentor();
                   break;
                case "3":
                   displayMentorProfile();
                   break;
                case "4":
                    createGroup();
                    break;
                case "5":
                    createNewLevelOfExperience();
                    break;
                case "0":
                   isDone = true;
                   break;
            }
            view.handlePause();
        }
    }

    public void createMentor(){
        String firstName = view.getUserInput("Enter first name: ");
        String lastName = view.getUserInput("Enter last name: ");
        String password = view.getUserInput("Enter password: ");
        MentorModel mentor = new MentorModel(firstName, lastName, password);
        view.clearScreen();
        view.displayMessage("Mentor created: " + mentor.toString());
    }

    public void editMentor() {
        List<MentorModel> mentors = getMentors();
        view.showAllMentors(prepareMentorsToDisplay(mentors));
        String id = view.getUserInput("Enter ID of mentor: ");
        for (MentorModel mentor : mentors) {
            if (id.equals(Integer.toString(mentor.getId()))) {
                boolean isFinished = false;
                while(! isFinished) {
                    view.clearScreen();
                    view.displayEditMenu();
                    String userChoice = view.getUserInput("Select an option: ");
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
                            String groupName = view.getUserInput("Enter group name: ");
                            mentor.setGroup(new GroupModel(groupName));
                            break;
                        case "0":
                            MentorDAO dao = new MentorDAO();
                            dao.saveObject(mentor);
                            isFinished = true;
                            break;
                    }
                    if(! isFinished){
                        view.displayMessage("Edited: " + mentor.toString());
                        view.handlePause();
                    }
                }
            }
        }
    }

    public void displayMentorProfile(){
        List<MentorModel> mentors = getMentors();
        view.showAllMentors(prepareMentorsToDisplay(mentors));
        String id = view.getUserInput("Enter ID of mentor: ");
        for (MentorModel mentor : mentors) {
            if (id.equals(Integer.toString(mentor.getId()))) {
                String mentorToDisplay = mentor.toString();
                view.clearScreen();
                view.displayMessage("Mentor's profile:");
                view.displayMessage(mentorToDisplay);
            }
        }
    }

    public void createGroup(){
        String groupName = view.getUserInput("Enter group name: ");
        GroupModel group = new GroupModel(groupName);
        view.clearScreen();
        view.displayMessage("Group created: " + group);
    }

    public void createNewLevelOfExperience(){
        SchoolController school = new SchoolController();
        ExperienceLevels experienceLevels = school.getExperienceLevels();
        view.displayMessage(experienceLevels.toString());
        view.displayMessage("That's all for now :P ");
    }
}
