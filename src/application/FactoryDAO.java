package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FactoryDAO implements CreatableDAO {

    protected String DEFAULT_TABLE;
    protected DbManagerDAO dao;

    public Object getObjectById(int id) {
        String query = "Select * from " + DEFAULT_TABLE + " WHERE id=" + id + ";";
        return getOneObject(query);
    }

    public List<Object> getObjects() {
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
}
