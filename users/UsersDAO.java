package users;

import application.AbstractDAO;

public class UsersDAO extends AbstractDAO{

    public UsersDAO(){
        defaultFileName = "users.csv";
        defaultFilePath = "DataFiles/users.csv";
    }
}
