package controllers;

import java.util.List;

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
//        view.displayLoadingStars();
        while (! shouldExit){
            handleIntro();
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
                    view.displayAuthors();
                    break;
                case "0":
                    shouldExit = true;
                    handleOutro();
                    databaseDao.closeConnection();
            }
        }
    }

    private UserController loggingProcedure() {
        String login = view.getLogin();
        String password = view.getPassword();
        UserController controller = null;
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