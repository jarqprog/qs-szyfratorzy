package application;

import users.*;
import users.LogableDAO;
// import users.UserCtrl;
import users.StudentModel;
import users.AdminModel;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


// testImports:
import users.MentorDAO;
import users.MentorModel;
import users.AdminDAO;
import users.AdminModel;
import users.StudentDAO;
import users.StudentModel;
import item.ItemsDAO;
import users.AdminModel;

public class RootController{

    private UsersDAO dao;
    private RootView view;
    // private UserCtrl loggedUser;
    public RootController()
    {
        dao = new UsersDAO();
        view = new RootView();
    }

    public void runApplication()
    {
        boolean running = true;
        while (running)
        {
            view.displayIntro();
            String userInput = view.displayLoginScreen("Choose: ");
            switch (userInput)
            {
                case "1": 
                    String [] userData = loggingProcedure();
                    handleUserData(userData);
                    break;
                case "0":
                    running = false;
            }
        }
        

        //runTest();
    }

    private String [] loggingProcedure() {
        String login = view.displayLoginScreen("Login: ");
        String password = view.displayLoginScreen("Password: ");
        dao.updateLoadedTables();
        String [] userDate = dao.importUserData(login, password);
        return userDate;
    }

    private void handleUserData(String [] userData) {
        if(userData.length == 0) {
            view.displayMessage("Invalid login or password!");
            view.handlePause();
        }
        else {
            if(userData[0].equals("admin")){
                AdminModel admin = dao.createAdminModel(userData);
                AdminController adminController = new AdminController(admin);
                adminController.handleMainMenu();
            }
            else if(userData[0].equals("mentor")){
                MentorModel mentor = dao.createMentorModel(userData);
                MentorController mentorController = new MentorController(mentor);
                mentorController.handleMainMenu();
            }
            else if(userData[0].equals("student")){
                StudentModel student = dao.createStudentModel(userData);
                StudentController studentController = new StudentController(student);
                studentController.handleMainMenu();
            }
        }
    }

    private void runTest(){
        System.out.println("Nothing to test...");

    }

}
