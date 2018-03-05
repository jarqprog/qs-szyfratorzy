package dao;

import model.PassiveModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class PassiveModelDAOImpl<T extends PassiveModel> implements PassiveModelDAO<T> {

    protected String DEFAULT_TABLE;
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    PassiveModelDAOImpl(Connection connection) {
        this.connection = connection;
        setDefaultTable();
    }

    protected abstract void setDefaultTable();
}
