package application;

import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.DatabaseMetaData;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;


public class DbManagerDAO extends DatabaseDAO {

    public void inputData(String query){
        openConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.commit();
        } catch ( Exception e ){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        closeConnection();
    }

    public List<String[]> getData(String query){

        /// implementation

        return new ArrayList<String[]>();

    }

    public List<String[]> getData(String query, String[] columnLabels){
        openConnection();
        List<String[]> dataByColumns = new ArrayList<String[]>();
        String[] data;
        for (String columnLabel : columnLabels){
            data = getDataByColumn(query, columnLabel);
            dataByColumns.add(data);
        }
        closeConnection();
        return dataByColumns;
    }


    protected String[] getDataByColumn(String query, String columnLabel){
        try{
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<String> columnData = new ArrayList<String>();
            while(result.next()){
                Object object = result.getObject(columnLabel);
                String data = String.valueOf(object);
                columnData.add(data);
            }
            statement.close();
            result.close();

            return columnData.toArray(new String[0]);

        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
}
