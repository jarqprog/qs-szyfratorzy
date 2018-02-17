package model;

import factory.ObjDaoFactory;

public abstract class AbsObject {

    @SuppressWarnings("unchecked")
    public void saveObject() {
        ObjDaoFactory.get(getClass()).save(this);
    }
}
