package school;

import application.AbstractView;

public class SchoolView extends AbstractView{

    public void displayExpLevelsManager() {
        String[] options = {"      *** Experience level manager ***     \n",
                " [1] display experience levels",
                " [2] restore default experience levels",
                " [3] modify experience level",
                " [4] create new experience level",
                " [5] delete experience level",
                " [0] exit manager\n"};

        for(String element : options) {
            System.out.println(element);
        }
    }

}
