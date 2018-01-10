package application;

import users.UsersDAO;
// import users.UserCtrl;
import users.StudentModel;
import users.AdminModel;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class RootController
{

    private AbstractDAO dao;
    private RootView view;
    // private UserCtrl loggedUser;
    public RootController()
    {
        dao = new UsersDAO();
        view = new RootView();
    }

    public void runApplication()
    {
        runTest();
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
        System.out.println(String.valueOf(dao.checkIfFileExist()));
        List<String> list = dao.getDataFromFile();
        String[] array = new String[list.size()];
        list.toArray(array);
        view.displayElementsOfCollection(array);
        list.add("Lolo na koniec!");
        dao.saveData(list);
        // AdminModel admin = dao.createFirstAdmin();
        // view.displayMessage(admin.getUserFirstName());
    }
}
