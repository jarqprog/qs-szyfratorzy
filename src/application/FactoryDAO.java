package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class FactoryDAO implements CreatableDAO {

    protected String DEFAULT_TABLE;
    protected DbManagerDAO dao;

    public Object getObjectById(int id){
        String query = "Select * from " + DEFAULT_TABLE + " WHERE id=" + id + ";";
        return getOneObject(query);
    }

    public List<Object> getObjects(){
        String query = "Select * from " + DEFAULT_TABLE + ";";
        return getAllObjects(query);
    }

    protected List<Object> getAllObjects(String query) {
        DbManagerDAO dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        List<Object> objects = new ArrayList<>();
        for (String[] record : dataCollection) {
            Object object = getOneObject(record);
            objects.add(object);
        }
        return objects;
    }

    public abstract Object getOneObject(String[] data);

    public abstract Object getOneObject(String query);

}
