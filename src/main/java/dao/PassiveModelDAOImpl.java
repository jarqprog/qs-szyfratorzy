package dao;

import enums.Table;
import model.PassiveModel;

import java.sql.Connection;

public abstract class PassiveModelDAOImpl<T extends PassiveModel> implements PassiveModelDAO<T> {

    private String DEFAULT_TABLE;
    private Connection connection;

    protected PassiveModelDAOImpl(Connection connection) {
        this.connection = connection;
        setDefaultTable();
    }

    protected abstract void setDefaultTable();

    protected String getDefaultTable() {
        return DEFAULT_TABLE;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void setDefaultTable(Table defaultTable) {
        this.DEFAULT_TABLE = defaultTable.getName();
    }
}
