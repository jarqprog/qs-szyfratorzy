package users;

import java.util.ArrayList;

public class AdminController
{
    private AdminView adminView;
    private MentorDAO mentorDAO;

    public AdminController()
    {
        adminView = new AdminView();
        mentorDAO = new MentorDAO();
    }

    public static void main(String[] args) {
        AdminController a = new AdminController();
        a.handleMainMenu();
    }

    public void handleMainMenu()
    {
        boolean exit = false;
        while(!exit){
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "0"};
            Boolean choiceIsReady = false;
            while(! choiceIsReady){
                adminView.clearScreen();
                adminView.displayMenu();
                userChoice = adminView.getUserInput("Select an option: ");
                choiceIsReady = checkIfElementInArray(correctChoices, userChoice);
            }
            adminView.clearScreen();
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
                case "0":
                   exit = true;
                   break;
            }
            adminView.handlePause();
        }
    }

    public Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }

    public void createMentor()
    {
        String firstName = adminView.getUserInput("Enter firstname: ");
        String lastName = adminView.getUserInput("Enter lastname: ");
        String password = adminView.getUserInput("Enter password: ");
        MentorModel newMentor = new MentorModel(firstName, lastName, password);
    }

    public void editMentor()
    {
        String firstNameToSearch = adminView.getUserInput("Enter firstname: ");
        for (MentorModel mentor : mentorDAO.getTestMentors())
        {
            if (firstNameToSearch.equals(mentor.getUserFirstName()))
            {
                boolean isFinished = false;
                while(!isFinished)
                {
                    adminView.clearScreen();
                    adminView.displayEditMenu();
                    String userChoice = adminView.getUserInput("Select an option: ");
                    switch(userChoice)
                    {
                        case "1" : 
                            String firstname = adminView.getUserInput("Enter firstname: "); 
                            mentor.setUserFirstName(firstname); 
                            break;
                        case "2" : 
                            String lastname = adminView.getUserInput("Enter lastname: "); 
                            mentor.setUserLastName(lastname); 
                            break;
                        case "3" : 
                            String password = adminView.getUserInput("Enter pasword: "); 
                            mentor.setUserPassword(password); 
                            break;
                        case "4" : 
                            String email = adminView.getUserInput("Enter email: "); 
                            mentor.setUserEmail(email); 
                            break;
                        case "5" : 
                            char group = adminView.getUserInput("Enter group: ").charAt(0); 
                            mentor.setMentorGroupName(group); 
                            break;
                        case "0":
                            isFinished = true;
                            break;
                    }
                }
            }
        }
    }

    public void displayMentorProfile()
    {
        String firstNameToSearch = adminView.getUserInput("Enter firstname of mentor: ");
        for (MentorModel mentor : mentorDAO.getTestMentors())
        {
            if (firstNameToSearch.equals(mentor.getUserFirstName()))
            {
                adminView.displayMentorProfile(mentor);
            }
        }
    }
}

