package dao;

import model.PassiveModel;

public interface PassiveModelDAO<T extends PassiveModel> extends CommonModelDAO<T> {

    boolean saveModel(T t);
}
