package controllers;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoFactory;
import model.User;
import dao.ArtifactDAO;
import model.Artifact;
import dao.QuestDAO;
import model.Quest;
import view.UsersView;

public abstract class  UserControllerImpl implements UserController {

    public abstract void executeMainMenu();

    protected <T extends User> void showProfile(T user){
        UsersView view = new UsersView();
        view.displayUserWithDetails(user);
    }

    protected List<Artifact> getArtifacts(){
        List<Artifact> artifacts = new ArrayList<>();
        try {
            artifacts = DaoFactory.getByType(ArtifactDAO.class).getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts;
    }

    protected List<Quest> getQuests(){
        List<Quest> quests = new ArrayList<>();
        try {
            quests = DaoFactory.getByType(QuestDAO.class).getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
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