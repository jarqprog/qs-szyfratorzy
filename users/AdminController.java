package users;

import java.util.ArrayList;

import school.SchoolModel;

public class AdminController extends UserController{
    private AdminModel admin;
    private AdminView view;
    private SchoolModel school;
    private AdminDAO dao;

    public AdminController(AdminModel adminModel){

        admin = adminModel;
        view = new AdminView();
        school = new SchoolModel();
        dao = new AdminDAO();
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
            switch(userChoice)
            {
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
                    createNewLevelOfExpirence();
                    break;
                case "0":
                   isDone = true;
                   break;
            }
            view.handlePause();
        }
    }

    public void createMentor(){
        String firstName = view.getUserInput("Enter firstname: ");
        String lastName = view.getUserInput("Enter lastname: ");
        String password = view.getUserInput("Enter password: ");
        MentorModel newMentor = new MentorModel(firstName, lastName, password);
        dao.saveModelToFile(newMentor);
    }

    public void editMentor(){
        String firstNameToSearch = view.getUserInput("Enter firstname: ");
        for (MentorModel mentor : dao.getMentorsFromFile()){

            if (firstNameToSearch.equals(mentor.getUserFirstName()))
            {
                boolean isFinished = false;
                while(!isFinished)
                {
                    view.clearScreen();
                    view.displayEditMenu();
                    String userChoice = view.getUserInput("Select an option: ");
                    switch(userChoice)
                    {
                        case "1" :
                            String firstname = view.getUserInput("Enter firstname: ");
                            mentor.setUserFirstName(firstname);
                            break;
                        case "2" :
                            String lastname = view.getUserInput("Enter lastname: ");
                            mentor.setUserLastName(lastname);
                            break;
                        case "3" :
                            String password = view.getUserInput("Enter pasword: ");
                            mentor.setUserPassword(password);
                            break;
                        case "4" :
                            String email = view.getUserInput("Enter email: ");
                            mentor.setUserEmail(email);
                            break;
                        case "5" :
                            Character group = view.getUserInput("Enter group: ").charAt(0);
                            mentor.setMentorGroupName(group);
                            break;
                        case "0":
                            dao.saveModelToFile(mentor);
                            isFinished = true;
                            break;
                    }
                }
            }
        }
    }

    public void displayMentorProfile()
    {
        String firstNameToSearch = view.getUserInput("Enter firstname of mentor: ");
        for (MentorModel mentor : dao.getMentorsFromFile())
        {
            if (firstNameToSearch.equals(mentor.getUserFirstName()))
            {
                view.displayMentorProfile(mentor);
            }
        }
    }

    public void createGroup(){
        Character newGroup = view.getUserInput("Enter group name: ").charAt(0);
        school.addGroup(newGroup);
    }

    public void createNewLevelOfExpirence(){
        String levelName = view.getUserInput("Enter level name: ");
        Integer expirence = Integer.parseInt(view.getUserInput("Enter expirence: "));
        school.addExpirenceLevel(levelName, expirence);
    }
}
