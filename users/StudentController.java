package users;

public class StudentController
{
    StudentModel student;

    public StudentController()
    {
        student = new StudentModel("firstName", "lastName", "password");
    }

    public void displayMyWallet()
    {
        System.out.println(student.getWallet());
    }

    public void displayMyLevelOfExpirence()
    {
        System.out.println(student.getExpirence());
    }
}