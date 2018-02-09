package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DbManagerDAO extends DatabaseDAO {

    public void inputData(String query){
        openConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.commit();
        } catch ( Exception e ){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally{
            closeConnection();
        }
    }

    public List<String[]> getData(String query){
        openConnection();
        final List<String[]> rowList = new ArrayList<>();
        try{
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData meta = resultSet.getMetaData();
            int colCounter = meta.getColumnCount();

            while (resultSet.next()) {
                List<String> columnList = new ArrayList<>();
                for (int column = 1; column <= colCounter; ++ column) {
                    Object value = resultSet.getObject(column);
                    columnList.add(String.valueOf(value));
                }
                String[] columnArray = new String[columnList.size()];
                columnArray = columnList.toArray(columnArray);
                rowList.add(columnArray);
            }
            statement.close();
            resultSet.close();

            return rowList;

        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;

        } finally {
          closeConnection();
        }
    }
}
