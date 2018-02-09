package users;


import java.util.List;

import Model.User;
import dao.ArtifactDAO;
import item.ArtifactModel;
import dao.QuestDAO;
import item.QuestModel;

public abstract class  UserController{

    public abstract void handleMainMenu();

    protected void showUsers(String[] users){
        UsersView view = new UsersView();
        view.displayUsers(users);

    }

    protected <T extends User> void showProfile(T user){
        UsersView view = new UsersView();
        view.displayUserWithDetails(user);
    }

    protected List<ArtifactModel> getArtifacts(){
        return new ArtifactDAO().getAllObjects();
    }

    protected List<QuestModel> getQuests(){
        return new QuestDAO().getAllObjects();
    }

    protected void executeNotImplementedInfo() {
        UsersView view = new UsersView();
        view.displayMessage("Not implemented yet");
    }

    protected <T> String[] prepareObjectsToDisplay(List<T> objects) {
        String[] objectsToDisplay = new String[objects.size()];
        int index = 0;
        for(T object : objects){
            objectsToDisplay[index] = object.toString();
            index ++;
        }
        return objectsToDisplay;
    }
}
