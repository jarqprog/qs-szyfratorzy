package model;

import factory.ModelFactory;

public interface UserFactory<T extends User> extends ModelFactory {

    T create(String firstName, String lastName, String password);
}
