package application;

import users.UsersDAO;
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
        // runTest();
        loggingProcedure();
    }

    private void loggingProcedure() {
        String login = view.displayLoginScreen("Login: ");
        String password = view.displayLoginScreen("Password: ");
        dao.updateLoadedTables();
        String [] userDate = dao.importUserData(login, password);
        System.out.println(Arrays.toString(userDate));
    }

    private void runTest(){

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
