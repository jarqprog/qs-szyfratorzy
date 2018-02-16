package dao;

import enums.FilePath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;

public class DatabaseDAO extends DAO{


    private final static String SQL_SCRIPT_PATH = FilePath.SQL_SCRIPT.getPath();
    private final static String DATA_BASE_PATH = FilePath.DATA_BASE.getPath();

    protected Connection connection = null;

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

    private void executeSqlScript(File inputFile) {
        String s;
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            String[] instructions = sb.toString().split(";");
            openConnection();
            Statement st = connection.createStatement();

            for(String instruction : instructions) {
                if(!instruction.trim().equals("")) {
                    st.executeUpdate(instruction);
                }
            }
        } catch(Exception e) {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        } finally {
            closeConnection();
        }
    }
}
