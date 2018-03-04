package managers;

import factory.ConnectionFactory;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DbProcessManager {
    // it will be changed or removed (methods delegates to proper DAOs)

//    private Connection connection;

//    public void inputData(String query){
//
//        connection = ConnectionFactory.getConnection();
//        Statement stmt;
//
//        try {
//            stmt = connection.createStatement();
//            stmt.executeUpdate(query);
//            stmt.close();
//        } catch ( Exception e ){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//    }

    public static boolean executeBatch(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
        }
        return true;
    }

    public static boolean executeUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
        }
        return true;
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
