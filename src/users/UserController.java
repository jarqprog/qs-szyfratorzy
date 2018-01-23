package users;
import application.AbstractView;


public abstract class  UserController{

    application.DAO DAO;
    AbstractView view;

    public abstract void handleMainMenu();

    public void showUsers(String[] users){
        UsersView userView = new UsersView();
        userView.displayUsers(users);

    }

    protected Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }

}
