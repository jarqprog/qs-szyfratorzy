package factory;

import model.Item;

public interface ItemFactory <T extends Item> extends Factory{

    T create(String name, String description, int value);
}
