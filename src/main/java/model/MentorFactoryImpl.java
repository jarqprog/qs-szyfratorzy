package model;

public class MentorFactoryImpl implements UserFactory {
    
    public Mentor create(String firstName, String lastName, String password) {

        Mentor user = new Mentor(firstName, lastName, password);
        int id = ModelDaoFactory.getByType(MentorDAO.class).saveObjectAndGetId(user);
        user.setId(id);
        return user;
    }
}
