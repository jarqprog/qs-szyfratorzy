package application;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


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

        return true;
    }

    public void saveData(List<String>){


    }
    public void createDefaultFile(){


    }

}
