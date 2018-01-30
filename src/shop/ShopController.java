package shop;

import java.util.List;
import item.ArtifactDAO;
import item.ArtifactModel;

public class ShopController {
    Shop shop;
    ShopView view;
    ArtifactDAO artifactDao;
//    ArtifactModel artifact;

    public ShopController (Shop shop) {
        this.shop = shop;
        view = new ShopView();
        artifactDao = new ArtifactDAO();
        List<ArtifactModel> artifacts = artifactDao.getManyObjects("SELECT * FROM artifacts;");
        shop.setStore(artifacts);
        }

    public void executeShoppingMenu() {
        boolean isDone = false;
        while(! isDone){
            String userChoice = "";
            String[] correctChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
            Boolean isChoiceReady = false;
            while(! isChoiceReady){
                view.clearScreen();
                view.displayMenu();
                userChoice = view.getUserInput("Select an option: ");
                isChoiceReady = checkIfElementInArray(correctChoices, userChoice);
            }
            view.clearScreen();
            switch(userChoice){
                case "1":
                    view.displayListOfArtifacts(shop.getStore());
                    break;
                case "0":
                    isDone = true;
                    break;
            }
            view.handlePause();
        }
    }

    private Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }
}
