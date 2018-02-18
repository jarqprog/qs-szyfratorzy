package model;

import factory.Factory;
import model.StudentSets;

public interface StudentSetFactory<T extends StudentSets> extends Factory {

    T create(String name);
}
