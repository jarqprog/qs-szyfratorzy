package model;

import dao.MentorDAO;

public class MentorFactoryImpl implements UserFactory {
    
    public Mentor create(String firstName, String lastName, String password) {

        Mentor user = new Mentor(firstName, lastName, password);
        int id =  new MentorDAO().saveObjectAndGetId(user);
        user.setId(id);
        return user;
    }
}
