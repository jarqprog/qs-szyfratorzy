package dao;

import model.ActiveModel;

import java.sql.SQLException;
import java.util.List;

public interface ActiveModelDAO<T extends ActiveModel> extends CommonModelDAO<T> {

    T getModelById(int id) throws SQLException;
    List<T> getAllObjects() throws SQLException;
//    T getOneObject(String query);
//    List<T> getManyObjects(String query);

}
