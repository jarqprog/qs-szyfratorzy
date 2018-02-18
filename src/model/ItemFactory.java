package model;

import factory.ObjFactory;

public interface ItemFactory <T extends Item> extends ObjFactory {

    T create(String name, String description, int value);
}
