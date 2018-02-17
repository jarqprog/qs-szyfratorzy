package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDAOImpl extends DAO implements FileDAO, FileFinderDAO {

    private String filePath;
    private BufferedReader bufReader;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedWriter bufWriter;

    public FileDAOImpl(String newFilePath) {
        filePath = newFilePath;
        prepareFile(filePath);
    }

    public String getDataByLine(int lineNumber) {
        List<String> data = getData();
        System.out.println(data.size());
        if (lineNumber < data.size()) {
            return data.get(lineNumber);
        }
        return "there's no such line!";
    }

    public List<String> getFilteredData(String filter) {
        String forRegex = String.format(".*(%s).*", filter.toLowerCase());
        Pattern regex = Pattern.compile(forRegex);
        Matcher matcher;
        List<String> data = getData();
        List<String> filtered = new ArrayList<>();
        for(String str: data) {
            matcher = regex.matcher(str.toLowerCase());
            if(matcher.find()) {
                filtered.add(str);
            }
        }
        return filtered;
    }

    public void clearFile() {
        try{
            openWriters(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeWriters();
        }
    }

    public void insertData(List<String> data){
        try{
            openWriters(true);
            for(String element : data) {
                bufWriter.append(element);
                bufWriter.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriters();
        }
    }

    public void insertData(List<String> data, boolean shouldAppend){
        try{
            openWriters(shouldAppend);
            for(String element : data) {
                bufWriter.append(element);
                bufWriter.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriters();
        }
    }

    public List<String> getData() {
        List<String> outputData = new ArrayList<>();
        String line;
        try {
            openReaders();
            while ((line = bufReader.readLine()) != null) {
                outputData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReaders();
        }
        return outputData;
    }

    private void openWriters(boolean shouldAppend) {
        fileWriter = null;
        bufWriter = null;

        try {
            fileWriter = new FileWriter(filePath, shouldAppend);
            bufWriter = new BufferedWriter(fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openReaders() {
        fileReader = null;
        bufReader = null;

        try {
            fileReader = new FileReader(filePath);
            bufReader = new BufferedReader(fileReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeReaders() {
        closeWritersOrReaders(bufReader);
        closeWritersOrReaders(fileReader);
    }

    private void closeWriters() {
        closeWritersOrReaders(bufWriter);
        closeWritersOrReaders(fileWriter);
    }

    private <T extends Closeable> void closeWritersOrReaders(T t) {
        try {
            if (t != null) {
                t.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
