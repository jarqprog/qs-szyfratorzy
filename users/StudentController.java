package users;

public class StudentController
{
    StudentModel student;
    StudentView studentView;

    public StudentController()
    {
        student = new StudentModel("firstName", "lastName", "password");
    }

    public void displayMyWallet()
    {
        studentView.displayWallet(student);
    }

    public void displayMyLevelOfExpirence()
    {
        studentView.displayLevelOfExpirence(student);
    }
}