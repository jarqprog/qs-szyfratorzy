package factory;

import model.User;

public interface UserFactory<T extends User> extends Factory{

    T create(String firstName, String lastName, String password);
}
