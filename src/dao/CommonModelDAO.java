package dao;

import model.CommonModel;

public interface CommonModelDAO<T extends CommonModel> {

    void save(T t);

}
