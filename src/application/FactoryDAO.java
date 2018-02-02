package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import users.*;

public abstract class FactoryDAO implements CreatableDAO {

    protected String DEFAULT_TABLE;
    protected DbManagerDAO dao;

    public <T> T getObjectById(int id) {
        String query = "Select * from " + DEFAULT_TABLE + " WHERE id=" + id + ";";
        try{
            @SuppressWarnings("unchecked")
            T object = (T) getOneObject(query);
            return object;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    public <T> List<T> getAllObjects() {
        String query = "Select * from " + DEFAULT_TABLE + ";";
        return getObjects(query);
    }

    public <T> List<T> getManyObjects(List<String[]> dataCollection) {
        List<T> collection = new ArrayList<>();
        for (String [] record : dataCollection) {
            @SuppressWarnings("unchecked")
            T object = (T) getOneObject(record);
            collection.add(object);
        }
        return collection;
    }

    public <T> List<T> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public abstract Object getOneObject(String[] data);

    public abstract Object getOneObject(String query);

    protected String[] getCurrentIdCollection() {
        final String query = String.format("SELECT id FROM %s;", this.DEFAULT_TABLE);
        int idIndex = 0;
        DbManagerDAO dao = new DbManagerDAO();
        List<String[]> currentIdsCollection = dao.getData(query);
        String[] currentIds = new String[currentIdsCollection.size()];
        for (int i = 0; i < currentIdsCollection.size(); i++) {
            currentIds[i] = currentIdsCollection.get(i)[idIndex];
        }
        return currentIds;
    }

    protected String getNewId(String[] oldIdCollection, String[] newIdCollection){
        for(String id : newIdCollection){
            // compare collections: old collection doesn't contain new id:
            if(! Arrays.asList(oldIdCollection).contains(id)){
                return id;
            }
        }
        return null;
    }

    public abstract <T> void saveObject(T t);

    public <T> void saveObjects(List<T> objects) {

        for(T object : objects) {
            saveObject(object);
        }
    }

    public <T> int saveObjectAndGetId(T t){
        String[] idsBefore = getCurrentIdCollection();
        saveObject(t);
        String[] idsAfter = getCurrentIdCollection();
        String id = getNewId(idsBefore, idsAfter);
        try {
            return Integer.parseInt(id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    private <T> List<T> getObjects(String query) {
        DbManagerDAO dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        List<T> objects = new ArrayList<>();
        try {
            for (String[] record : dataCollection) {
                @SuppressWarnings("unchecked")
                T object = (T) getOneObject(record);
                objects.add(object);
            }
            return objects;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
}
