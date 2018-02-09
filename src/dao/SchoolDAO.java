package dao;

import java.util.List;
import java.util.ArrayList;

public class SchoolDAO {

    private DbManagerDAO dao;

    public SchoolDAO() {
         dao = new DbManagerDAO();
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
