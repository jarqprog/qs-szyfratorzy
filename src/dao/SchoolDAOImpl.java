package dao;

import managers.ResultSetManager;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class SchoolDAO {

    private Connection connection;

    public SchoolDAO(Connection connection) {
        this.connection = connection;
    }

    public List<String> getStudentsSetsNames(String studentSetTable) {
        List<String> names = new ArrayList<>();
        String query = "SELECT name FROM " + studentSetTable + ";";
        List<String[]> data = dao.getData(query);
        int NAME_INDEX = 0;
        for(String[] table : data){
            names.add(table[NAME_INDEX]);
        }
        return names;
    }
}
