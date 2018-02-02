package application;

import java.util.List;

public interface CreatableDAO {

    <T> T getObjectById(int id);
    <T> List<T> getAllObjects();
    <T> T getOneObject(String query);
    <T> List<T> getManyObjects(String query);

}
