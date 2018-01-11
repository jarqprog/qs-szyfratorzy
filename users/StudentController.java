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

    }
}
