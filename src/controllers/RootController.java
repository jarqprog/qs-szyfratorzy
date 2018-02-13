package controllers;

import java.util.List;

import dao.*;
import enums.FilePath;
import enums.Table;
import model.Admin;
import model.Mentor;
import model.Student;
import view.RootView;


public class RootController{

    private RootView view;
    private DbManagerDAO dbManagerDao;

    public RootController() {
        view = new RootView();
        dbManagerDao = new DbManagerDAO();

    }

    public void runApplication(){
        view.clearScreen();
        prepareDatabase();
        boolean isDone = false;
        String[] correctChoices = {"0", "1", "2"};
        while (! isDone){
            handleIntro();
            view.displayLoginScreen();
            String userInput = view.getMenuChoice(correctChoices);
            switch (userInput) {
                case "1":
                    loggingProcedure();
                    break;
                case "2":
                    view.displayAuthors();
                    break;
                case "0":
                    isDone = true;
                    handleOutro();
            }
        }
    }

    private void loggingProcedure() {

        String login = view.getLogin();
        String password = view.getPassword();
        String [] usersTables = {Table.ADMINS.getName(), Table.MENTORS.getName(), Table.STUDENTS.getName()};
        for(String tableName : usersTables) {
            String query = String.format("Select * FROM %s " +
            "WHERE first_name || id = '%s' AND password = '%s';", tableName, login, String.valueOf(password));
            List<String[]> userData = dbManagerDao.getData(query);
            if(userData.size() == 1) {
                createUser(userData.get(0), tableName);
                break;
            }
        }
    }

    private void createUser(String [] userData, String tableName) {

        if(tableName.equals(Table.ADMINS.getName())) {
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.getOneObject(userData);
            AdminController adminController = new AdminController(admin);
            adminController.handleMainMenu();

        } else if(tableName.equals(Table.MENTORS.getName())){
            MentorDAO mentorDAO = new MentorDAO();
            Mentor mentor = mentorDAO.getOneObject(userData);
            MentorController mentorController = new MentorController(mentor);
            mentorController.handleMainMenu();

        } else if(tableName.equals(Table.STUDENTS.getName())){
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getOneObject(userData);;
            StudentController studentController = new StudentController(student);
            studentController.handleMainMenu();
        }
    }

    private void handleIntro(){
        IntroOutroDAO ioDao = new IntroOutroDAO();
        List<String> introData = ioDao.getRawDataFromFile();
        view.displayIntro(introData);
    }

    private void handleOutro(){
        IntroOutroDAO ioDao = new IntroOutroDAO();
        String filePath = FilePath.OUTRO.getPath();
        List<String> outroData = ioDao.getRawDataFromFile(filePath);
        view.displayOutro(outroData);
    }

    private void prepareDatabase(){

        DatabaseDAO dbDAO = new DatabaseDAO();
        dbDAO.openConnection();
        boolean hasConnection = dbDAO.isConnected();
        if(hasConnection){
            view.displayMessage("\n\n");
            view.displayMessageInNextLine("Loading database successfully\n\n");
            view.displayLoadingStars();
        }else{
            view.displayMessageInNextLine("Problem occured while opening database");
        }
        dbDAO.fillDatabase();
        dbDAO.closeConnection();
        view.displayMessage("\n\n");
        view.displayMessageInNextLine("Database prepared..\n\n");
        view.handlePause();
    }
}