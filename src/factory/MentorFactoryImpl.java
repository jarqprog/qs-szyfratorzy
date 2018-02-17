package factory;

import dao.MentorDAO;
import model.Group;
import model.Mentor;

public class MentorFactoryImpl implements UserFactory {
    
    public Mentor create(String firstName, String lastName, String password) {

        Mentor user = new Mentor(firstName, lastName, password);
        int id =  new MentorDAO().saveObjectAndGetId(user);
        user.setId(id);
        return user;
    }
}
