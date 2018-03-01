package dao;

import controllers.UserController;
import exceptions.LoginFailure;


public interface LoginDAO {

    UserController getUserControllerByLoginAndPassword(String login, String password) throws LoginFailure;
}




