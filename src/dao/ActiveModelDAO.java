package dao;

import model.ActiveModel;

import java.util.List;

public interface ActiveModelDAO<T extends ActiveModel> extends CommonModelDAO<T> {

    T getObjectById(int id);
    List<T> getAllObjects();
    T getOneObject(String query);
    List<T> getManyObjects(String query);

}
