package dao;

import model.ActiveObject;

import java.util.List;

public interface ActiveObjDAO<T extends ActiveObject> {

    T getObjectById(int id);
    List<T> getAllObjects();
    T getOneObject(String query);
    List<T> getManyObjects(String query);
}
