package users;

public class AdminView extends UsersView {
    public void displayMenu() {
        String[] options = {"      *** Admin's Menu ***     \n",
                            "[1] display profile",
                            "[2] create mentor",
                            "[3] edit mentor",
                            "[4] display mentor",
                            "[5] display mentor's students",
                            "[6] create group",
                            "[7] manage experience levels",
                            "[0] exit\n"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayEditMenu() {
        String[] options = {"      *** Mentor Editor ***     \n",
                            "[1] edit first name",
                            "[2] edit last name",
                            "[3] edit password",
                            "[4] edit email",
                            "[5] edit group",
                            "[0] exit\n"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
