package controllers;

import java.util.List;

import dao.*;
import enums.FilePath;
import exceptions.LoginFailure;
import managers.*;
import managers.DbManagerImpl;
import managers.DbManager;
import view.RootView;


public class RootController {

    private RootView view;
    private DbManager dbManager;
    private boolean shouldExit;

    public RootController() {
        view = new RootView();
        dbManager = new DbManagerImpl();
        shouldExit = false;
    }

    public void runApplication(){
        view.clearScreen();
        dbManager.prepareDatabase();
        view.displayLoadingStars();
        while (! shouldExit){
            executeIntro();
            String[] correctChoices = {"0", "1", "2"};
            view.displayLoginScreen();
            String userInput = view.getMenuChoice(correctChoices);
          
            switch (userInput) {
                case "1":
                    UserController controller = loggingProcedure();
                    if (controller != null) {
                        controller.executeMainMenu();
                    }
                    break;
                case "2":
                    showAuthors();
                    break;
                case "0":
                    shouldExit = true;
                    executeOutro();
                    dbManager.closeConnection();
            }
        }
    }

    private UserController loggingProcedure() {
        String login = view.getLogin();
        String password = view.getPassword();
        UserController controller = null;
        try {
            controller = SpecialDaoFactory.getByType(LoginDAO.class).getUserControllerByLoginAndPassword(login, password);
        } catch (LoginFailure ex) {
            view.clearScreen();
            view.displayMessage(ex.getMessage());
            view.handlePause();
        }
        return controller;
    }

    private void executeIntro() {
        String introFilePath = FilePath.INTRO.getPath();
        FileManager manager = new FileManagerImpl(introFilePath);
        List<String> introData = manager.getData();
        view.displayCollectionData(introData);
    }

    private void executeOutro() {
        String outroFilePath = FilePath.OUTRO.getPath();
        FileManager manager = new FileManagerImpl(outroFilePath);
        List<String> outroData = manager.getData();
        view.displayCollectionData(outroData);
        view.handlePause();
    }

    private void showAuthors() {
        String authorsFilePath = FilePath.AUTHORS.getPath();
        FileManager manager = new FileManagerImpl(authorsFilePath);
        List<String> introData = manager.getData();
        view.displayCollectionData(introData);
        view.handlePause();
    }
}