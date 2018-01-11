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

    public void displayMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] display wallet",
                            "[2] display level of expirence",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}