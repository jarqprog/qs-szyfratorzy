package users;

import application.AbstractView;

public class StudentView extends AbstractView
{
    public void displayWallet(StudentModel student)
    {
        System.out.println(student.getWallet());
    }

    public void displayLevelOfExpirence(StudentModel student)
    {
        System.out.println(student.getExperience());
    }
}