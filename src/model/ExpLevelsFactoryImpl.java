package model;

import factory.ModelFactory;

public class ExpLevelsFactoryImpl implements ModelFactory {

    public ExperienceLevels create() {
        return new ExperienceLevels();
    }

}