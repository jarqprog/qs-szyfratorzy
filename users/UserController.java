package users;
import application.AbstractDAO;
import application.AbstractView;

public abstract class  UserController{

    AbstractDAO DAO;
    AbstractView view;

    public abstract void handleMainMenu();
    public String showUsers(){
        return "";
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
