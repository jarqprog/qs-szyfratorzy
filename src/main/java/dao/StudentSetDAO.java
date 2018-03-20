package dao;

import managers.SQLProcessManager;
import model.StudentSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class StudentSetDAO<T extends StudentSet> extends ActiveModelDAOImpl<T>{

    protected StudentSetDAO(Connection connection) {
        super(connection);
    }

    public boolean saveModel(T studentSet) {
        String studentSetId = String.valueOf(studentSet.getId());
        String name = studentSet.getName();
        String query;

        if(studentSetId.equals("-1")){
            query = String.format(
                    "INSERT INTO %s VALUES(null, ?)", getDefault_Table());
        } else{
            query = String.format("UPDATE %s SET name=? " +
                    "WHERE id=?", getDefault_Table());
        }
        try {
            PreparedStatement preparedStatement;
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            if(!studentSetId.equals("-1")) {
                preparedStatement.setInt(2, Integer.valueOf(studentSetId));
            }
            SQLProcessManager.executeUpdate(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
