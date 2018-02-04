package application;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.File;

public class DatabaseDAO extends DAO{


    private final static String SQL_SCRIPT_PATH = FilePath.SQL_SCRIPT.getPath();
    private final static String DATA_BASE_PATH = FilePath.DATA_BASE.getPath();

    protected Connection connection = null;


    public String getSqlFilePath(){
        return SQL_SCRIPT_PATH;
    }

    public String getDatabasePath(){
        return DATA_BASE_PATH;
    }

    public boolean isConnected(){
        if(connection == null){
            return false;
        }
        return true;
    }

    public void fillDatabase(){
        File sqlFile = new File(SQL_SCRIPT_PATH);
        executeSqlScript(sqlFile);
    }

    public void updateDatabase(String sqlFilePath){
        if(connection == null){
            openConnection();
        }
        File sqlFile = new File(sqlFilePath);
        executeSqlScript(sqlFile);
        closeConnection();
    }

    public void openConnection() {
        if(connection == null) {
            try {
                String className = "org.sqlite.JDBC";
                Class.forName(className);
                String url = "jdbc:sqlite:" + DATA_BASE_PATH;
                connection = DriverManager.getConnection(url);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                connection = null;
            }
        }
    }

    public void closeConnection() {
        if(connection != null){
            try{
                connection.close();
            } catch (Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            } finally{
                connection = null;
            }
        }
    }

    public void executeSqlScript(File inputFile){
        String delimiter = ";";
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }
        Statement currentStatement = null;
        while(scanner.hasNext()) {
            String rawStatement = scanner.next() + delimiter;
            try {
                currentStatement = connection.createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
        scanner.close();
    }
}
