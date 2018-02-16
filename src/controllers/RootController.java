package controllers;

import java.util.List;
import java.io.Console;

import dao.*;
import enums.FilePath;
import exceptions.LoginFailure;
import view.RootView;


public class RootController {

    private RootView view;
    private DatabaseDAO databaseDao;
    private boolean shouldExit;

    public RootController() {
        view = new RootView();
        databaseDao = new DatabaseDAOImpl();
        shouldExit = false;
    }

    public void runApplication(){
        view.clearScreen();
        databaseDao.prepareDatabase();
        while (! shouldExit){
            handleIntro();
            String userInput = view.displayLoginScreen("Please, choose an option: ");
            switch (userInput) {
                case "1":
                    UserCtrl controller = loggingProcedure();
                    if (controller != null) {
                        controller.handleMainMenu();
                    }
                    break;
                case "0":
                    shouldExit = true;
                    handleOutro();
                    databaseDao.closeConnection();
            }
        }
    }

    private UserCtrl loggingProcedure() {
        String login = view.displayLoginScreen("Login: ");
        Console console = System.console();
        view.displayMessage("Please enter your password: ");
        String password = String.valueOf(console.readPassword());
        UserCtrl controller = null;
        try {
            controller = LoginDAO.getUserControllerByLoginAndPassword(login, password);
        } catch (LoginFailure ex) {
            view.clearScreen();
            view.displayMessage(ex.getMessage());
            view.handlePause();
        }
        return controller;
    }

    private void handleIntro(){
        String introFilePath = FilePath.INTRO.getPath();
        FileDAO dao = new FileDAOImpl(introFilePath);
        List<String> introData = dao.getData();
        view.displayIntro(introData);
    }

    private void handleOutro(){
        String outroFilePath = FilePath.OUTRO.getPath();
        FileDAO dao = new FileDAOImpl(outroFilePath);
        List<String> outroData = dao.getData();
        view.displayOutro(outroData);
    }
}