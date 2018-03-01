package dao;

import model.PassiveModel;

public interface PassiveModelDAO<T extends PassiveModel> extends CommonModelDAO<T> {

    void save(T t);
}
