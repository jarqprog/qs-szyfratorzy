package users;
import application.AbstractDAO;
import application.AbstractView;

public abstract class  UserController{

    AbstractDAO DAO;
    AbstractView view;

    public abstract void handleMainMenu();
}
