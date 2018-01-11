package users;

import application.AbstractDAO;

public class UsersDAO extends AbstractDAO implements LogableDAO{

    public UsersDAO(){
        defaultFileName = "users.csv";
        defaultFilePath = "DataFiles/users.csv";
        prepareFile();
    }

    public AdminModel createFirstAdmin(){
        return new AdminModel("admin", "admin", "admin");
    }

}
