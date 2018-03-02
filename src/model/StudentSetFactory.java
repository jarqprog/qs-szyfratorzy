package model;

import factory.ModelFactory;

public interface StudentSetFactory<T extends StudentSets> extends ModelFactory {

    T create(String name);
}
