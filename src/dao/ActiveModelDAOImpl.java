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
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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

//    public List<T> getManyObjects(String query) {
//        manager = new ResultSetManager();
//        List<String[]> dataCollection = manager.getData(query);
//        return getManyObjects(dataCollection);
//    }

    public abstract T extractModel(String[] data);

//    public T getOneObject(String query) {
//        manager = new ResultSetManager();
//        String[] record = manager.getData(query).get(0);
//        try {
//            return extractModel(record);
//        } catch(NullPointerException e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    public abstract void save(T t);

    public void saveObjects(List<T> objects) {

        for(T object : objects) {
            save(object);
        }
    }

    public int saveObjectAndGetId(T t){
        try {
            String[] idsBefore = getCurrentIdCollection();
            save(t);
            String[] idsAfter = getCurrentIdCollection();
            String id = getNewId(idsBefore, idsAfter);
            if(id != null) return Integer.parseInt(id);
        } catch(SQLException ex) {
        System.out.println(ex.getMessage());
        }
        return -1;
    }

    private String[] getCurrentIdCollection() throws SQLException {
        
        int idIndex = 0;
        String[] currentIds = new String[0];
        String query = String.format("Select %s from %s", "id", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        List<String[]> currentIdsCollection = ResultSetManager.getObjectsDataCollection(resultSet);

        if (currentIdsCollection != null) {
            currentIds = new String[currentIdsCollection.size()];
            for (int i = 0; i < currentIdsCollection.size(); i++) {

                currentIds[i] = currentIdsCollection.get(i)[idIndex];
            }
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
