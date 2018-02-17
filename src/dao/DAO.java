package dao;

import java.io.File;
import java.io.IOException;


public class DAO {

    protected String filePath;

    protected void createNewFile(String filePath){
        try {
            File f = new File(filePath);
            f.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Boolean checkIfFileExist(String filePath){
        File f = new File(filePath);
        return f.exists();
    }

    protected void prepareFile(String filePath){
        if(! checkIfFileExist(filePath)){
            createNewFile(filePath);
        }
    }
}
