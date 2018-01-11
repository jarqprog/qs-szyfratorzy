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
        String [] userData = loggingProcedure();
        handleUserData(userData);
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
        // view.displayIntro();
        // view.displayLoginScreen();
        // StudentModel student = new StudentModel("Jarek", "Kucharczyk", "123");
        // String message = student.getUserFirstName()+", role: " + student.getUserRole();
        // view.displayMessage(message);
        // view.handlePause();
        // view.displayLogoutScreen();
        // view.displayOutro();
        // System.out.println(String.valueOf(dao.checkIfFileExist()));
        // List<String> list = dao.getDataFromFile();
        // String[] array = new String[list.size()];
        // list.toArray(array);
        // view.displayElementsOfCollection(array);
        // list.add("Lolo na koniec!");
        // dao.saveData(list);
        // AdminModel admin0 = dao.createFirstAdmin();
        // view.displayMessage(admin0.getUserFirstName());

        /// mentors test:
        // MentorDAO mentorDao = new MentorDAO();
        // List<MentorModel> mentors = mentorDao.getTestMentors();
        // view.displayMessage("Mentors");
        // for(MentorModel mentor : mentors){
        //     System.out.println(" -"+mentor);
        // }

        // /// admins test:
        // AdminDAO adminDao = new AdminDAO();
        // List<AdminModel> admins = adminDao.getTestAdmins();
        // view.displayMessage("Admins");
        // for(AdminModel admin : admins){
        //     System.out.println(" -"+admin);
        // }

        ///
        /// admins test:
        StudentDAO studentDao = new StudentDAO();
        List<StudentModel> students = studentDao.getTestStudents();
        view.displayMessage("Students");
        for(StudentModel student : students){
            System.out.println(" -"+student);
        }
        ItemsDAO itemsDao = new ItemsDAO();

        /// create model by DAO
        UsersDAO newDao = new UsersDAO();
        String[] table1 = {"student", "12", "Jarek", "Kuc", "1231415", "A"};
        String[] table2 = {"mentor", "13", "Konrad", "Lapka", "asad", "B"};
        String[] table3 = {"admin", "14", "Ryszard", "Kowal", "asad"};
        StudentModel jarek = newDao.createStudentModel(table1);
        MentorModel konrad = newDao.createMentorModel(table2);
        AdminModel adminek = newDao.createAdminModel(table3);
        System.out.println(jarek);
        System.out.println(konrad);
        System.out.println(adminek);
        newDao.saveLastId(22, "DataFiles/maxUserId.csv");
        System.out.println(newDao.loadLastId("DataFiles/maxUserId.csv"));

    }
}
