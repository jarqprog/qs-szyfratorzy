package application;

//import java.util.Arrays;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.Reader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Array;
//import java.sql.SQLException;
//import java.sql.SQLFeatureNotSupportedException;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.DatabaseMetaData;
//import java.io.InputStreamReader;
//import java.io.FileNotFoundException;
//import java.io.File;

public abstract class DatabaseDAO extends DAO{

// Fields:
//    protected final static String SQL_SRIPT_PATH = "..." - to fill database;
//    protected Connection connection = null;


//    tu metody związane z bazą danych


//    public String getSqlFilePath(){
//        return SQL_SRIPT_PATH;
//    }
//
//    public boolean isConnected(){
//        if(connection == null){
//            return false;
//        }
//        return true;
//    }
//
//    public void fillDatabase(){
//        File sqlFile = new File(SQL_SRIPT_PATH);
//        executeSqlScript(sqlFile);
//    }
//
//    public void openConnection(){
//        try {
//            String className = "org.sqlite.JDBC";
//            Class.forName(className);
//            String url = "jdbc:sqlite:" + defaultFilePath;
//            connection = DriverManager.getConnection(url);
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage());
//            connection = null;
//        }
//    }
//
//    public void closeConnection(){
//        if(connection != null){
//            try{
//                connection.close();
//            } catch (Exception e){
//                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            } finally{
//                connection = null;
//            }
//        }
//    }
//
//    public void executeQuery(String query){
//        openConnection();
//        Statement stmt = null;
//        try {
//            connection.setAutoCommit(false);
//            stmt = connection.createStatement();
//            stmt.executeUpdate(query);
//            stmt.close();
//            connection.commit();
//        } catch ( Exception e ){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//        closeConnection();
//    }


//    public void executeSqlScript(File inputFile){
//        String delimiter = ";";
//        Scanner scanner;
//        try {
//            scanner = new Scanner(inputFile).useDelimiter(delimiter);
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//            return;
//        }
//        Statement currentStatement = null;
//        while(scanner.hasNext()) {
//            String rawStatement = scanner.next() + delimiter;
//            try {
//                currentStatement = connection.createStatement();
//                currentStatement.execute(rawStatement);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                if (currentStatement != null) {
//                    try {
//                        currentStatement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                currentStatement = null;
//            }
//        }
//        scanner.close();
//    }

//    public List<String[]> getDataByColumns(String query, String[] columnLabels){
//        openConnection();
//        ResultSet result;
//        List<String[]> dataByColumns = new ArrayList<String[]>();
//        String[] data;
//        for (String columnLabel : columnLabels){
//            data = getDataByColumn(query, columnLabel);
//            dataByColumns.add(data);
//        }
//        closeConnection();
//        return dataByColumns;
//    }
//
//    protected String[] getDataByColumn(String query, String columnLabel){
//        try{
//            connection.setAutoCommit(false);
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(query);
//            List<String> columnData = new ArrayList<String>();
//            while(result.next()){
//                Object object = result.getObject(columnLabel);
//                String data = String.valueOf(object);
//                columnData.add(data);
//            }
//            statement.close();
//            result.close();
//            return columnData.toArray(new String[0]);
//        } catch(Exception e){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            return null;
//        }
//    }


}
