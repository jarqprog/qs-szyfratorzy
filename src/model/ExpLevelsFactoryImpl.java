package model;

import factory.ObjFactory;

public class ExpLevelsFactoryImpl implements ObjFactory {

    public ExperienceLevels create() {
        return new ExperienceLevels();
    }

}