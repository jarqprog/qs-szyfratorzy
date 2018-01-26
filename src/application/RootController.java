package application;

import java.util.List;
import java.util.Arrays;

import users.*;
import application.DbManagerDAO;

import java.io.Console;


public class RootController{

    private LogableDAO dao;
    private RootView view;
    private DbManagerDAO dbManagerDao;

    public RootController() {
        dao = new UsersDAO();
        view = new RootView();
        dbManagerDao = new DbManagerDAO();

    }

    public void runApplication(){
        prepareDatabase();
//        runTest();  // TEST!!!
        boolean isDone = false;
        while (! isDone){
            handleIntro();
            String userInput = view.displayLoginScreen("Please, choose an option: ");
            switch (userInput)
            {
                case "1":
                    loggingProcedure();
//                    handleUserData(userData);
                    break;
                case "0":
                    isDone = true;
                    handleOutro();
            }
        }
    }

    private void loggingProcedure() {

        String login = view.displayLoginScreen("Login: ");
        Console console = System.console();
        view.displayMessage("Please enter your password: ");
        char[] password = console.readPassword();
        String [] usersTables = {"admins", "mentors", "students"};
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

    private void handleUserData(UserModel user) {

//            createUser(user);
    }

    public void createUser(String [] userData, String tableName) {

        if(tableName.equals("admins")) {
            AdminDAO adminDAO = new AdminDAO();
            AdminModel admin = adminDAO.getOneObject(userData);
            AdminController adminController = new AdminController(admin);
            adminController.handleMainMenu();

        } else if(tableName.equals("mentors")){
            MentorDAO mentorDAO = new MentorDAO();
            MentorModel mentor = mentorDAO.getOneObject(userData);
            MentorController mentorController = new MentorController(mentor);
            mentorController.handleMainMenu();

        } else if(tableName.equals("students")){
            StudentDAO studentDAO = new StudentDAO();
            StudentModel student = studentDAO.getOneObject(userData);;
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
        List<String> outroData = ioDao.getRawDataFromFile("DataFiles/outro.txt");
        view.displayOutro(outroData);
    }

    private void prepareDatabase(){

        DatabaseDAO dbDAO = new DatabaseDAO();
        dbDAO.openConnection();
        boolean hasConnection = dbDAO.isConnected();
        if(hasConnection){
            view.displayMessage("Opened database successfully");
        }else{
            view.displayMessage("Problem occured while opening database");
        }
        dbDAO.fillDatabase();
        dbDAO.closeConnection();
        view.displayMessage("Database prepared");
        view.handlePause();
    }

//    private void runTest(){
//        DbManagerDAO dao = new DbManagerDAO();
//        String insertQuery =
//                "INSERT OR IGNORE INTO admins VALUES(5,'Maciek','Jablonowski','maciek@gmail.com','12321');";
//        dao.inputData(insertQuery);
//        String insertQuery01 =
//                "INSERT OR IGNORE INTO admins VALUES(6,'Piotr','Gryzlo','piotrek@cc.pl','12321');";
//        dao.inputData(insertQuery01);
//        String query = "SELECT * FROM admins;";
//
//        List<String[]> collectionNew = dao.getData("SELECT * FROM admins;");
//        for(String[] lista : collectionNew){
//            System.out.println(Arrays.toString(lista));
//        }
//
//        StudentModel student;
//        StudentDAO stuDAO = new StudentDAO();
//        List<StudentModel> students = stuDAO.getManyObjects("Select * from students where last_name = 'Kucharczyk';");
//        student = students.get(0);
//        System.out.println(student);
//
//
//        List<String[]> collectionNew1 = dao.getData("SELECT * FROM students;");
//        for(String[] lista1 : collectionNew1){
//            System.out.println(Arrays.toString(lista1));
//        }
//
//        StudentModel Artur = stuDAO.getOneObject("SELECT * FROM students WHERE first_name='Artur';");
//        System.out.println(Artur);
//        Artur.setEmail("arturro@gmail.com");
//        stuDAO.saveObject(Artur);
//        StudentModel Artur1 = stuDAO.getOneObject("SELECT * FROM students WHERE first_name='Artur';");
//        System.out.println(Artur1);
//        view.handlePause();
//    }
}