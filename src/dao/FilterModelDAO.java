package dao;

import model.CommonModel;

import java.util.List;

public interface FilterModelDAO<T extends CommonModel> extends CommonModelDAO<T> {

    List<T> getFilteredModelsByIntegerParameter(String parameter, int parameterValue);
}
