package model;

import enums.Role;

public class Admin extends User {


    Admin(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        setRole(Role.ADMIN.getName());
    }

    Admin(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
        setRole(Role.ADMIN.getName());
    }
}
