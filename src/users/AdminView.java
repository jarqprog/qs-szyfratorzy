package users;

import application.AbstractView;

public class AdminView extends AbstractView
{
    public void displayMenu() {
        String[] options = {"      *** Menu ***     ",
                            "[1] create mentor",
                            "[2] edit mentor",
                            "[3] display mentor",
                            "[4] create group",
                            "[5] create level of expirence",
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

    public void showAllMentors(String[] mentors)
    {
        for (String mentor : mentors)
        {
            System.out.println(mentor);
        }
    }
}
