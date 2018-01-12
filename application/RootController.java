package application;


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import users.LogableDAO;
import users.UsersDAO;
import users.MentorModel;
import users.AdminModel;
import users.StudentModel;
import users.MentorController;
import users.AdminController;
import users.StudentController;
import java.io.Console;


public class RootController{

    private UsersDAO dao;
    private RootView view;
    public RootController(){

        dao = new UsersDAO();
        dao.prepareAdmin();
        view = new RootView();
    }

    public void runApplication(){
        boolean isDone = false;
        while (! isDone){

            view.displayIntro();
            String userInput = view.displayLoginScreen("Choose: ");
            switch (userInput)
            {
                case "1":
                    String [] userData = loggingProcedure();
                    handleUserData(userData);
                    break;
                case "0":
                    isDone = true;
            }
        }
    }

    private String [] loggingProcedure() {
        String login = view.displayLoginScreen("Login: ");
        Console console = System.console();
        System.out.println("Please enter your password: ");
        char[] password = console.readPassword();
        dao.updateLoadedTables();
        String [] userDate = dao.importUserData(login, String.valueOf(password));
        return userDate;
    }

    private void handleUserData(String [] userData) {
        if(userData.length == 0) {
            view.displayMessage("Invalid login or password!");
        }
        else {
            createUser(userData);
        }
    }

    public void createUser(String [] userData) {
        int ROLE_INDEX = 0;
        if(userData[ROLE_INDEX].equals("admin")){
            AdminModel admin = dao.createAdminModel(userData);
            AdminController adminController = new AdminController(admin);
            adminController.handleMainMenu();
        }
        else if(userData[ROLE_INDEX].equals("mentor")){
            MentorModel mentor = dao.createMentorModel(userData);
            MentorController mentorController = new MentorController(mentor);
            mentorController.handleMainMenu();
        }
        else if(userData[ROLE_INDEX].equals("student")){
            StudentModel student = dao.createStudentModel(userData);
            StudentController studentController = new StudentController(student);
            studentController.handleMainMenu();
        }
    }

    private void runTest(){
        System.out.println("Nothing to test...");

    }

}
