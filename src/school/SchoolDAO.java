package school;

import application.DbManagerDAO;
import application.Table;

import java.util.HashMap;
import java.util.List;

public class SchoolDAO {

    final private String EXPERIENCE_LEVELS_TABLE = Table.EXPERIENCE_LEVELS.getName();
    private DbManagerDAO dao;

    public SchoolDAO(){
         dao = new DbManagerDAO();
    }

    public HashMap<String, Integer> loadExperienceLevels(){
        String levelName;
        Integer levelValue;
        int LEVEL_NAME_INDEX = 0;
        int LEVEL_VALUE_INDEX = 1;
        HashMap<String, Integer> experienceLevels = new HashMap<>();
        final String query = String.format("SELECT level_name, level_value FROM %s;", EXPERIENCE_LEVELS_TABLE);

        List<String[]> dataCollection = dao.getData(query);
        for(String[] data : dataCollection){
            levelName = data[LEVEL_NAME_INDEX];
//            System.out.println
            levelValue = Integer.parseInt(data[LEVEL_VALUE_INDEX]);
            experienceLevels.put(levelName, levelValue);
        }
        return experienceLevels;
    }
}
