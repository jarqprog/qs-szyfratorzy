package dao;

import model.PassiveModel;

import java.sql.Connection;

public abstract class PassiveModelDAOImpl<T extends PassiveModel> implements PassiveModelDAO<T> {

    protected String DEFAULT_TABLE;
    protected Connection connection;

    PassiveModelDAOImpl(Connection connection) {
        this.connection = connection;
        setDefaultTable();
    }

    protected abstract void setDefaultTable();
}
