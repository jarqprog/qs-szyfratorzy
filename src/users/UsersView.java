package users;
import application.AbstractView;

public class UsersView extends AbstractView{
    public void displayUsers(String[] users){
        for(String element: users){
            System.out.println(element);
        }
    }
}
