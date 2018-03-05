package model;

import dao.DaoFactory;

public abstract class CommonModel {
    // parent class of all BO in app

    public void saveModel() {
        String className = getClass().getSimpleName();
        DaoFactory.getByModel(className).saveModel(this);
    }
}
