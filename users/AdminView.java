package users;

import application.AbstractView;
import users.MentorModel;

public class AdminView extends AbstractView
{
    public void displayMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] create mentor",
                            "[2] edit mentor",
                            "[3] display mentor",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayEditMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] edit firstname",
                            "[2] edit lastname",
                            "[3] edit password",
                            "[4] edit email",
                            "[5] edit group",
                            "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayMentorProfile(MentorModel mentor)
    {
        System.out.println(mentor.toString() + " Group: "+ mentor.getMentorGroupName());
    }
}