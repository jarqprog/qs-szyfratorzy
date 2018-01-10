package application;

import users.UsersDAO;
// import users.UserCtrl;
import users.StudentModel;


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
        // view.displayIntro();
        // view.displayLoginScreen();
        // StudentModel student = new StudentModel("Jarek", "Kucharczyk", "123");
        // String message = student.getUserFirstName()+", role: " + student.getUserRole();
        // view.displayMessage(message);
        // view.handlePause();
        // view.displayLogoutScreen();
        // view.displayOutro();
        System.out.println(String.valueOf(dao.checkIfFileExist()));
    }
}
