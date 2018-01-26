package application;

import java.util.List;

public interface CreatableDAO {

    public Object getObjectById(int id);
    public List<Object> getObjects();
    public abstract Object getOneObject(String[] data);
    public abstract Object getOneObject(String query);

}
