package dao;

import controllers.UserController;
import exceptions.LoginFailure;


public interface LoginDAO extends SpecialDAO {

    UserController getUserControllerByLoginAndPassword(String login, String password) throws LoginFailure;
}




