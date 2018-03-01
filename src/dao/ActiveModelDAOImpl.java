package dao;

import managers.TemporaryManager;
import model.ActiveModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActiveModelDAOImpl<T extends ActiveModel> implements ActiveModelDAO<T> {

    protected String DEFAULT_TABLE;
    protected TemporaryManager dao;

    public T getObjectById(int id) {
        String query = "Select * from " + DEFAULT_TABLE + " WHERE id=" + id + ";";
        return getOneObject(query);
    }

    public List<T> getAllObjects() {
        String query = "Select * from " + DEFAULT_TABLE + ";";
        return getObjects(query);
    }

    private List<T> getManyObjects(List<String[]> dataCollection) {
        List<T> collection = new ArrayList<>();
        for (String [] record : dataCollection) {
            T object = getOneObject(record);
            collection.add(object);
        }
        return collection;
    }

    public List<T> getManyObjects(String query) {
        dao = new TemporaryManager();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public abstract T getOneObject(String[] data);

    public T getOneObject(String query) {
        dao = new TemporaryManager();
        String[] record = dao.getData(query).get(0);
        try {
            return getOneObject(record);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public abstract void save(T t);

    public void saveObjects(List<T> objects) {

        for(T object : objects) {
            save(object);
        }
    }

    public int saveObjectAndGetId(T t){
        String[] idsBefore = getCurrentIdCollection();
        save(t);
        String[] idsAfter = getCurrentIdCollection();
        String id = getNewId(idsBefore, idsAfter);
        try {
            if(id != null) return Integer.parseInt(id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    private List<T> getObjects(String query) {
        TemporaryManager dao = new TemporaryManager();
        List<String[]> dataCollection = dao.getData(query);
        List<T> objects = new ArrayList<>();
        try {
            for (String[] record : dataCollection) {
                T object = getOneObject(record);
                objects.add(object);
            }
            return objects;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    private String[] getCurrentIdCollection() {
        final String query = String.format("SELECT id FROM %s;", this.DEFAULT_TABLE);
        int idIndex = 0;
        TemporaryManager dao = new TemporaryManager();
        List<String[]> currentIdsCollection = dao.getData(query);
        String[] currentIds = new String[currentIdsCollection.size()];
        for (int i = 0; i < currentIdsCollection.size(); i++) {
            currentIds[i] = currentIdsCollection.get(i)[idIndex];
        }
        return currentIds;
    }

    private String getNewId(String[] oldIdCollection, String[] newIdCollection){
        for(String id : newIdCollection){
            // compare collections: old collection doesn't contain new id:
            if(! Arrays.asList(oldIdCollection).contains(id)){
                return id;
            }
        }
        return null;
    }
}
