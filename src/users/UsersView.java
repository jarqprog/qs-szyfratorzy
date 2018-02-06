package users;

import application.AbstractView;

import java.util.List;

public class UsersView extends AbstractView{

    protected void displayUsers(String[] users){
        for(String element: users){
            System.out.println(element);
        }
    }

    public <T extends UserModel> void displayUsers(List<T> users){
        for(T user: users) {
            displayObject(user);
        }
    }

    protected <T extends UserModel> void displayUsersWithDetails(List<T> users){
        for(T user: users) {
            displayUserWithDetails(user);
            System.out.println("\n");
        }
    }

    protected <T extends UserModel> void  displayUserWithDetails(T user){
        System.out.println(space + user.getFullDataToString());
    }

}