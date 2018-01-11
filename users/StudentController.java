package users;

public class StudentController
{
    StudentModel studentModel;
    StudentView studentView;

    public StudentController(StudentModel studentModel){}

    public void displayMyWallet()
    {
        studentView.displayWallet(studentModel);
    }

    public void displayMyLevelOfExpirence()
    {
        studentView.displayLevelOfExpirence(studentModel);
    }

    public void handleMainMenu()
    {
        boolean exit = false;
        while(!exit)
        {
            String userChoice = "";
            String[] correctChoices = {"1", "2","0"};
            Boolean choiceIsReady = false;
            while(! choiceIsReady)
            {
                studentView.clearScreen();
                studentView.displayMenu();
                userChoice = studentView.getUserInput("Select an option: ");
                choiceIsReady = checkIfElementInArray(correctChoices, userChoice);
            }
            studentView.clearScreen();
            switch(userChoice)
            {
                case "1":
                   displayMyWallet();
                   break;
                case "2":
                    displayMyLevelOfExpirence();
                   break;
                case "0":
                   exit = true;
                   break;
            }
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
}
