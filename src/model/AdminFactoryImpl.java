package model;

public class AdminFactoryImpl implements UserFactory {
    
    public Admin create(String firstName, String lastName, String password) {

        Admin user = new Admin(firstName, lastName, password);
        int id =  ModelDaoFactory.getByType(AdminDAO.class).saveObjectAndGetId(user);
        user.setId(id);
        return user;
    }
}
