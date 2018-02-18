package model;

import factory.ObjFactory;

public interface UserFactory<T extends User> extends ObjFactory {

    T create(String firstName, String lastName, String password);
}
