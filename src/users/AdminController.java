package users;

import java.util.List;
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
        MentorModel newMentor = new MentorModel(firstName, lastName, password);
        String mentorsToDisplay = newMentor.toString();
        view.clearScreen();
        view.displayMessage("Mentor created:");
        view.displayMessage(mentorsToDisplay);
        dao.saveModelToFile(newMentor);
    }

    public void editMentor(){
        view.showAllMentors(prepareMentorsToDisplay(dao.getMentorsFromFile()));
        String id = view.getUserInput("Enter ID of mentor: ");
        for (MentorModel mentor : dao.getMentorsFromFile())
        {
            if (id.equals(Integer.toString(mentor.getId())))
            {
                boolean isFinished = false;
                while(! isFinished)
                {
                    view.clearScreen();
                    view.displayEditMenu();
                    String userChoice = view.getUserInput("Select an option: ");
                    switch(userChoice)
                    {
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
                            Character group = view.getUserInput("Enter group: ").charAt(0);
                            mentor.setGroupName(group);
                            break;
                        case "0":
                            dao.saveModelToFile(mentor);
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

    public void displayMentorProfile()
    {
        view.showAllMentors(prepareMentorsToDisplay(dao.getMentorsFromFile()));
        String id = view.getUserInput("Enter ID of mentor: ");
        for (MentorModel mentor : dao.getMentorsFromFile())
        {
            if (id.equals(Integer.toString(mentor.getId())))
            {
                String mentorToDisplay = mentor.toString();
                view.clearScreen();
                view.displayMessage("Mentor's profile:");
                view.displayMessage(mentorToDisplay);
            }
        }
    }

    public void createGroup(){
        Character newGroup = view.getUserInput("Enter group name: ").charAt(0);
        school.addGroup(newGroup);
        view.clearScreen();
        view.displayMessage("Group created: " + String.valueOf(newGroup));
    }

    public void createNewLevelOfExperience(){
        String levelName = view.getUserInput("Enter level name: ");
        Integer experience = Integer.parseInt(view.getUserInput("Enter experience: "));
        school.addExperienceLevel(levelName, experience);
    }

    public String[] prepareMentorsToDisplay(List<MentorModel> mentors)
    {
        String[] mentorsToDisplay = new String[mentors.size()];
        int index = 0;
        for(MentorModel mentor : mentors){
            mentorsToDisplay[index] = mentor.toString();
            index ++;
        }
        return mentorsToDisplay;
    }
}
