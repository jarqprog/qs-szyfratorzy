package dao;

import managers.ResultSetManager;
import model.ActiveModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActiveModelDAOImpl<T extends ActiveModel> implements ActiveModelDAO<T> {

    protected String DEFAULT_TABLE;
    protected ResultSetManager dao;
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    ActiveModelDAOImpl(Connection connection) {
        this.connection = connection;
        setDefaultTable();
    }

    public T getModelById(int id) throws SQLException {
        String query = String.format("Select * from %s WHERE id=?", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String[] data = ResultSetManager.getObjectData(resultSet);
        return extractModel(data);
    }

    public List<T> getAllObjects() throws SQLException {
        List<T> objects = new ArrayList<>();
        String query = String.format("Select * from %s", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        List<String[]> dataCollection = ResultSetManager.getObjectsDataCollection(resultSet);
        if (dataCollection != null) {
            objects = getManyObjects(dataCollection);
        }
        return objects;
    }

    private List<T> getManyObjects(List<String[]> dataCollection) {
        List<T> collection = new ArrayList<>();
        for (String [] record : dataCollection) {
            T object = extractModel(record);
            collection.add(object);
        }
        return collection;
    }

    public List<T> getManyObjects(String query) {
        dao = new ResultSetManager();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public abstract T extractModel(String[] data);

    public T getOneObject(String query) {
        dao = new ResultSetManager();
        String[] record = dao.getData(query).get(0);
        try {
            return extractModel(record);
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

    private String[] getCurrentIdCollection() {
        final String query = String.format("SELECT id FROM %s;", this.DEFAULT_TABLE);
        int idIndex = 0;
        ResultSetManager dao = new ResultSetManager();
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

    protected abstract void setDefaultTable();

}
