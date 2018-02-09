package view;

import model.User;

import java.util.List;

public class UsersView extends AbstractView{

    public void displayUsers(String[] users){
        for(String element: users){
            System.out.println(element);
        }
    }

    public <T extends User> void displayUsers(List<T> users){
        for(T user: users) {
            displayObject(user);
        }
    }

    public <T extends User> void displayUsersWithDetails(List<T> users){
        for(T user: users) {
            displayUserWithDetails(user);
            System.out.println("\n");
        }
    }

    public <T extends User> void  displayUserWithDetails(T user){
        System.out.println(space + user.getFullDataToString());
    }

}