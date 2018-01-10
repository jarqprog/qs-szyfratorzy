package users;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminController
{
    private Scanner scanner;
    private ArrayList<MentorModel> list;

    public AdminController()
    {
        scanner = new Scanner(System.in);
        list = new ArrayList<MentorModel>();
    }

    public static void main(String[] args) {
        AdminController a = new AdminController();
        a.createMentor();
        a.editMentor();
        a.displayMentorProfile();
    }

    public void createMentor()
    {
        System.out.print("Enter firstname: ");
        String firstName = scanner.next();
        System.out.print("Enter lastname: ");
        String lastName = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter group: ");
        Character groupName = scanner.next().charAt(0);
        MentorModel newMentor = new MentorModel(firstName, lastName, password, groupName);
        list.add(newMentor);
        System.out.println("Done");
    }

    public void editMentor()
    {
        System.out.println("Enter email of mentor: ");
        String emailToSearch = scanner.next();
        for (MentorModel mentor : list)
        {
            if (emailToSearch.equals(mentor.getUserEmail()))
            {
                System.out.println("Choose: ");
                System.out.println("1. Edit firstname");
                System.out.println("2. Edit lastname");
                System.out.println("3. Edit password");
                System.out.println("4. Edit email");
                System.out.println("5. Change group");
                char userInput = scanner.next().charAt(0);
                switch(userInput)
                {
                    case '1' : System.out.print("Enter new firstname: "); String firstname = scanner.next(); mentor.setUserFirstName(firstname); break;
                    case '2' : System.out.print("Enter new lastname: "); String lastname = scanner.next(); mentor.setUserLastName(lastname); break;
                    case '3' : System.out.print("Enter new password: "); String password = scanner.next(); mentor.setUserPassword(password); break;
                    case '4' : System.out.print("Enter new email: "); String email = scanner.next(); mentor.setUserEmail(email); break;
                    case '5' : System.out.print("Enter new group: "); Character group = scanner.next().charAt(0); mentor.setMentorGroupName(group); break;
                }
            }
        }
    }

    public void displayMentorProfile()
    {
        System.out.println("Enter email of mentor: ");
        String emailToSearch = scanner.next();
        for (MentorModel mentor : list)
        {
            if (emailToSearch.equals(mentor.getUserEmail()))
            {
                System.out.println(String.format("ID: %d, Name: %s %s, Email: %s, Group: %c", mentor.getUserID(), mentor.getUserFirstName(), mentor.getUserLastName(), mentor.getUserEmail(), mentor.getMentorGroupName()));
            }
        }
    }
}

