package view;

public class MentorView extends UsersView {

    public int getItemValue() {
        String message = "Enter a value of item: ";
        return getNotNegativeNumberFromUser(message);
    }

    public void displayMenu() {
        String[] options = {"      *** Mentor's Menu ***\n",
                            "[1] display profile",
                            "[2] create student",
                            "[3] assign student to group",
                            "[4] assign student to team",
                            "[5] create quest",
                            "[6] edit quest",
                            "[7] create artifact",
                            "[8] edit artifact",
                            "[9] mark student quest",
                            "[10] display student's details",
                            "[11] mark student artifact",
                            "[12] display students from group",
                            "[13] create new team",
                            "[14] check attendance",
                            "[0] exit\n\n"};

        displayElementsOfCollection(options);
    }

    public void displayArtifactMenu() {
        String[] options = {"      *** Artifacts Editor ***\n",
                            "[1] edit artifact name",
                            "[2] edit artifact type",
                            "[3] edit artifact description",
                            "[4] edit artifact price",
                            "[0] exit\n\n"};

        displayElementsOfCollection(options);
    }

    public void displayQuestMenu() {
        String[] options = {"      *** Quest Editor ***\n",
                "[1] edit quest name",
                "[2] edit quest type",
                "[3] edit quest description",
                "[4] edit quest reward",
                "[0] exit\n\n"};

        displayElementsOfCollection(options);
    }
}
