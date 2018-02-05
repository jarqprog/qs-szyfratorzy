package school;

import application.AbstractView;

public class SchoolView extends AbstractView{

    public void displayExpLevelsManager() {
        String[] options = {"      *** Experience level manager ***     \n",
                " [1] display experience levels",
                " [2] restore default experience levels",
                " [3] modify an existing or create new experience level",
                " [4] delete experience level",
                " [5] delete ALL experience levels",
                " [0] exit manager\n"};

        for(String element : options) {
            System.out.println(element);
        }
    }
}
