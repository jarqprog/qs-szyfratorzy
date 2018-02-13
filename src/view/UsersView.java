package view;

import model.User;

import java.util.List;

public class UsersView extends AbstractView {

    public <T extends User> void  displayUserWithDetails(T user){
        System.out.println(doubleTab + user.getFullDataToString());
    }

    public <T extends User> void displayManyUsersWithDetails(List<T> users) {
        for (User user : users) {
            displayUserWithDetails(user);
        }
    }
}