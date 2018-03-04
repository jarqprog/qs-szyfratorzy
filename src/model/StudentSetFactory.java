package model;

import factory.ModelFactory;

public interface StudentSetFactory<T extends StudentSet> extends ModelFactory {

    T create(String name);
}
