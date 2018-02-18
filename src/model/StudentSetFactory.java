package model;

import factory.ObjFactory;

public interface StudentSetFactory<T extends StudentSets> extends ObjFactory {

    T create(String name);
}
