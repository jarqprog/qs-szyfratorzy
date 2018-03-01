package model;

import factory.DaoFactory;

public abstract class CommonModel {
    // parent class of all BO in app

    @SuppressWarnings("unchecked")
    public void saveObject() {
        String className = getClass().getSimpleName();
        DaoFactory.get(className).save(this);
    }
}
