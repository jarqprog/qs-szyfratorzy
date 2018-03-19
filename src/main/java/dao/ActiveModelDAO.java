package dao;

import model.ActiveModel;

import java.util.List;

public interface ActiveModelDAO<T extends ActiveModel> extends CommonModelDAO<T> {

    T getModelById(int id);
    List<T> getAllModels();

}
