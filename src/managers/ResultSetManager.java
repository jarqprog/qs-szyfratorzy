package managers;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetManager {
    // it will be changed or removed (methods delegates to proper DAOs)

    private Connection connection;

    public void inputData(String query){

        connection = ConnectionFactory.getConnection();
        Statement stmt;

        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch ( Exception e ){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static String[] getObjectData(ResultSet resultSet) {
        String[] objectData = new String[0];
        try {
            if (resultSet.isBeforeFirst()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                int colCounter = meta.getColumnCount();
                while (resultSet.next()) {
                    List<String> columnList = new ArrayList<>();
                    for (int column = 1; column <= colCounter; ++column) {
                        Object value = resultSet.getObject(column);
                        columnList.add(String.valueOf(value));
                    }
                    objectData = columnList.toArray(objectData);
                }
                resultSet.close();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return objectData;
    }

    public static List<String[]> getObjectsDataCollection(ResultSet resultSet) {

        List<String[]> objectsDataCollection = new ArrayList<>();

        try {
            if (resultSet.isBeforeFirst()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                int colCounter = meta.getColumnCount();
                while (resultSet.next()) {
                    List<String> columnList = new ArrayList<>();
                    for (int column = 1; column <= colCounter; ++column) {
                        Object value = resultSet.getObject(column);
                        columnList.add(String.valueOf(value));
                    }
                    String[] columnArray = new String[columnList.size()];
                    columnArray = columnList.toArray(columnArray);
                    objectsDataCollection.add(columnArray);
                }
                resultSet.close();
            }

        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return objectsDataCollection;
    }
}
