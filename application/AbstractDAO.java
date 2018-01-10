package application;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.Path;



public abstract class AbstractDAO
{

    protected String defaultFileName;
    protected String defaultFilePath;
    protected List<String> loadedData;

    public String getDefaultFileName(){
        return defaultFileName;
    }

    public String getDefaultFilePath(){
        return defaultFilePath;
    }

    public void setDefaultFileName(String name){
        defaultFileName = name;
    }

    public void setDefaultFilePath(String path){
        defaultFilePath = path;
    }

    public List<String> getDataFromFile(){
        List<String> outputData = new ArrayList<String>(); // tmp
        return outputData;
    }

    public Boolean checkIfFileExist(){
        File f = new File(defaultFilePath);
        return f.exists();
    }



    public void saveData(List<String> collectionToSave){


    }
    public void createDefaultFile(){


    }

}
