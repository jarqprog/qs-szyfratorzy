package dao;

import model.AbsObject;

public interface AbsObjDAO<T extends AbsObject> {

    void save(T t);

}
