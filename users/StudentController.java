package users;

public class StudentController extends UserController{
    StudentModel student;
    StudentView view;

    public StudentController(StudentModel studentModel){
        student = studentModel;
        view = new StudentView();
    }

    public void showMyWallet(){
        view.displayWallet(student.getWallet());
    }

    public void showLevelOfExperience(){
        view.displayLevelOfExperience(student.getExperience());
    }

    public void handleMainMenu(){
        boolean isDone = false;
        while(! isDone){

            String userChoice = "";
            String[] correctChoices = {"1", "2","0"};
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
                    showMyWallet();
                    break;
                case "2":
                    showLevelOfExperience();
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }
}
