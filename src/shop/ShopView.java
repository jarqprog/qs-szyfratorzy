package shop;

import application.AbstractView;
import item.ArtifactModel;
import java.util.List;

public class ShopView extends AbstractView {

    public void displayMenu() {
        String[] options = {"      *** ShopModel online ***     ",
                "[1] display available artifacts",
                "[2] buy artifact",
                "[3] buy artifact for team",
                "[0] exit"};

        for(String element : options) {
            System.out.println(element);
        }
    }

    public void displayListOfArtifacts(List<ArtifactModel> store) {
        for(ArtifactModel artifact : store) {
            System.out.println(artifact);
        }
    }
}
