package view;

import model.Attendance;
import model.Inventory;
import model.Item;
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

    public <T extends Item> void  displayItemWithDetails(T item){
        System.out.println(item.getFullDataToString());
    }

    public <T extends Inventory> void  displayInventoryWithDetails(T inventory){
        System.out.println(inventory.getFullDataToString());
    }

    public void displayAttendanceWithDetails(Attendance attendance) {
        System.out.println(attendance.getFullDataToString());
    }
}