package application;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.Path;



public abstract class AbstractDAO
{

    protected String defaultFileName;
    protected String defaultFilePath;
    protected List<String[]> loadedTables;
    protected List<String> loadedStrings;

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

    public List<String[]> getLoadedTables(){
        return loadedTables;
    }

    public List<String> getLoadedStrings(){
        return loadedStrings;
    }

    public void setLoadedTables(List<String[]> tablesCollection){
        loadedTables = tablesCollection;
    }

    public void setLoadedStrings(List<String> stringsCollection){
        loadedStrings = stringsCollection;
    }

    public List<String> getDataFromFile(){
        List<String> outputData = new ArrayList<String>();
        BufferedReader br = null;
        FileReader fr = null;
        String line;
        try {
            fr = new FileReader(defaultFilePath);
            br = new BufferedReader(fr);
            int array_index = 0;
            while ((line = br.readLine()) != null) {
                outputData.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    return outputData;
    }

    public Boolean checkIfFileExist(){
        File f = new File(defaultFilePath);
        return f.exists();
    }

    public void saveData(List<String> collectionToSave){
        FileWriter fileWriter = null;
        BufferedWriter bufWriter = null;
        try{
            fileWriter = new FileWriter(defaultFilePath);
            bufWriter = new BufferedWriter(fileWriter);
            for(String element : collectionToSave) {
                bufWriter.write(String.join(";", element) +  "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            if (bufWriter != null) {
                bufWriter.close();
            }

                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
            ex.printStackTrace();
            }
        }
    }

    public void createDefaultFile(){


    }

}
