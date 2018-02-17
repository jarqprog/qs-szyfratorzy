package dao;

import model.PassiveObject;

public interface PassiveObjDAO<T extends PassiveObject> extends AbsObjDAO<T> {

    void save(T t);
}
