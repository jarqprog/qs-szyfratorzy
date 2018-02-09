package dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public abstract class DAO {

    protected String defaultDatabasePath;
    protected String defaultFileName;
    protected String defaultFilePath;
    protected List<String[]> loadedTables;
    protected List<String> loadedStrings;


    public void saveData(){
        prepareFile();
        setLoadedStringsUsingLoadedTables();
        saveCollectionToFile(getLoadedStrings());
    }

    public String getDefaultDatabasePath(){
        return defaultDatabasePath;
    }

    public void setDefaultDatabasePath(String newDatabasePath){
        defaultDatabasePath = newDatabasePath;
    }

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

    protected List<String> getLoadedStrings(){
        return loadedStrings;
    }

    public void setLoadedTables(List<String[]> tablesCollection){
        loadedTables = tablesCollection;
    }

    public void setLoadedStrings(List<String> stringsCollection){
        loadedStrings = stringsCollection;
    }

    public void prepareFile(){
        if(! checkIfFileExist()){
            createDefaultFile();
        }
    }

    public void prepareFile(String filePath){
        if(! checkIfFileExist(filePath)){
            createNewFile(filePath);
        }
    }

    public Boolean checkIfFileExist(){
        File f = new File(defaultFilePath);
        return f.exists();
    }

    public Boolean checkIfFileExist(String filePath){
        File f = new File(filePath);
        return f.exists();
    }

    protected void setLoadedStringsUsingLoadedTables(){
        List<String> outputList = new ArrayList<String>();
        String elementToAdd;
        for(String[] table : loadedTables){
            elementToAdd = String.join(";", table);
            outputList.add(elementToAdd);
        }
        setLoadedStrings(outputList);
    }

    protected void createDefaultFile(){
        try {
            File f = new File(defaultFilePath);
            f.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void createNewFile(String filePath){
        try {
            File f = new File(filePath);
            f.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getRawDataFromFile(){
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

    public List<String> getRawDataFromFile(String filePath){
        List<String> outputData = new ArrayList<String>();
        BufferedReader br = null;
        FileReader fr = null;
        String line;
        try {
            fr = new FileReader(filePath);
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


    protected void saveCollectionToFile(List<String> collectionToSave){
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
    public List<String[]> getNestedTablesCollection(List<String> rawData){
        String[] nestedTable;
        List<String[]> outputData = new ArrayList<String[]>();
        for(String element : rawData){
            nestedTable = element.split(";");
            outputData.add(nestedTable);
        }
        return outputData;
    }
    public void updateLoadedTables(){
        List<String> rawData = getRawDataFromFile();
        String[] nestedTable;
        List<String[]> nestedTablesCollection = new ArrayList<String[]>();
        for(String element : rawData){
            nestedTable = element.split(";");
            nestedTablesCollection.add(nestedTable);
        }
        setLoadedTables(nestedTablesCollection);
    }

    public String[] findProperTableByChosenParameter(String dataToCompare, int indexInTable){
        updateLoadedTables();
        String[] properTable = new String[0];
        for(String[] table : loadedTables){
            if(checkIfDataMatches(dataToCompare, indexInTable, table)){
                properTable = table;
            }
        }
        return properTable;
    }

    public List<String[]> getCollectionTablesByChosenParameters(String dataToCompare, int indexInTable){
        updateLoadedTables();
        List<String[]> outputCollection = new ArrayList<String[]>();
        for(String[] table : loadedTables){
            if(checkIfDataMatches(dataToCompare, indexInTable, table)){
                outputCollection.add(table);
            }
        }
        return outputCollection;
    }

    protected Boolean checkIfDataMatches(String dataToCompare, int indexInTable, String[] table){
        return dataToCompare.equals(table[indexInTable]);
    }

    protected void removeDataIfIdAlreadyExists(String id, int idIndex){
        updateLoadedTables();
        for(Iterator<String[]> iterator = loadedTables.iterator(); iterator.hasNext();){
            String[] data = iterator.next();
            if(checkIfDataMatches(id, idIndex, data)){
                loadedTables.remove(data);
                break;
            }
        }
    }
}
