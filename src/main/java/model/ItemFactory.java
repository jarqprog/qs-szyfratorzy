package model;

import factory.ModelFactory;

public interface ItemFactory <T extends Item> extends ModelFactory {

    T create(String name, String description, int value);
}
