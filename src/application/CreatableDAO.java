package application;

import java.util.List;

public interface CreatableDAO {

    <T> T getObjectById(int id);
    <T> List<T> getAllObjects();
    Object getOneObject(String[] data);
    Object getOneObject(String query);

}
