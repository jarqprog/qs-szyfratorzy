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
        //String [] userData = loggingProcedure();
        //handleUserData(userData);

        runTest();
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
                dao.createAdminModel(userData);
            }
            else if(userData[0].equals("mentor")){
                dao.createMentorModel(userData);
            }
            else if(userData[0].equals("student")){
                dao.createStudentModel(userData);
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
        // StudentDAO studentDao = new StudentDAO();
        // List<StudentModel> students = studentDao.getTestStudents();
        // view.displayMessage("Students");
        // for(StudentModel student : students){
        //     System.out.println(" -"+student);
        // }
        // ItemsDAO itemsDao = new ItemsDAO();

        // /// create model by DAO
        // UsersDAO newDao = new UsersDAO();
        // AdminModel user = new AdminModel("s", "w", "e");
        // // AdminModel user1 = new AdminModel("s", "w", "e");
        // // AdminModel user2 = new AdminModel("s", "w", "e");
        // // System.out.println(user.getUserID());
        // // System.out.println(user1.getUserID());
        // // System.out.println(user2.getUserID());
        // // String[] table1 = {"student", "12", "Jarek", "Kuc", "1231415", "A"};
        // // String[] table2 = {"mentor", "13", "Konrad", "Lapka", "asad", "B"};
        // // String[] table3 = {"admin", "14", "Ryszard", "Kowal", "asad"};
        // // StudentModel jarek = newDao.createStudentModel(table1);
        // // MentorModel konrad = newDao.createMentorModel(table2);
        // // AdminModel adminek = newDao.createAdminModel(table3);
        // // System.out.println(jarek);
        // // System.out.println(konrad);
        // // System.out.println(adminek);
        // System.out.println(newDao.loadLastId("DataFiles/maxUserId.csv"));

    }
}
