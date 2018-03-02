package dao;

import controllers.*;
import enums.Table;
import exceptions.LoginFailure;
import factory.UserControllerFactory;
import managers.ResultSetManager;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    private Connection connection;

    LoginDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public UserController getUserControllerByLoginAndPassword(String login, String password) throws LoginFailure {
        User user;
        String [] usersTables = {Table.ADMINS.getName(), Table.MENTORS.getName(), Table.STUDENTS.getName()};
        String statement;
        for(String table : usersTables) {
            statement = String.format("SELECT * FROM %s WHERE first_name || id =? AND password=?", table);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    String[] userData = ResultSetManager.getObjectData(resultSet);
                    user = extractUser(userData, table);
                    if (user != null) {
                        return UserControllerFactory.getController(user);
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        throw new LoginFailure();
    }

    private User extractUser(String[] userData, String table) {
        User user = null;
        switch (table) {
            case ("admins"):
                AdminDAO adDao = new AdminDAO(connection);
                user = adDao.extractModel(userData);
                break;
            case ("mentors"):
                MentorDAO meDao = new MentorDAO(connection);
                user = meDao.extractModel(userData);
                break;
            case ("students"):
                StudentDAO stDao = new StudentDAO(connection);
                user = stDao.extractModel(userData);
                break;
        }
        return user;
    }
}
