package controllers;

import java.util.List;

import dao.*;
import enums.FilePath;
import exceptions.LoginFailure;
import factory.ConnectionFactory;
import managers.*;
import view.RootView;


public class RootController {

    private RootView view;
    private boolean shouldExit;

    public static RootController getInstance() {
        return new RootController();
    }

    private RootController() {
        view = new RootView();
        shouldExit = false;
        setDatabase();
    }

    public void runApplication(){
        view.clearScreen();
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
                    ConnectionFactory.shutdownConnections();
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

    private void setDatabase() {
        setSqliteDatabase();
    }

    private void setSqliteDatabase() {
        String url = "jdbc:sqlite:" + FilePath.DATA_BASE.getPath();
        String driver = "org.sqlite.JDBC";
        SQLManager sqlManager = SqliteManager.getManager(FilePath.DATA_BASE);
        DatabaseConfiguration dbConfig = DatabaseConfiguration
                .createSQLiteConfiguration(url, driver, 5, 7);
        DatabaseConnectionGetter databaseConnectionGetter = SQLConnectionGetter
                .getSqliteConMaker(dbConfig, sqlManager, FilePath.SQL_SCRIPT);
        ConnectionFactory.setSqlConnectionGetter(databaseConnectionGetter);
    }
}