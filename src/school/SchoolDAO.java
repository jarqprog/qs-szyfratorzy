package school;

import application.DbManagerDAO;
import application.Table;

import java.util.*;

public class SchoolDAO {

    final private String EXPERIENCE_LEVELS_TABLE = Table.EXPERIENCE_LEVELS.getName();
    private DbManagerDAO dao;

    public SchoolDAO() {
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
            levelValue = Integer.parseInt(data[LEVEL_VALUE_INDEX]);
            experienceLevels.put(levelName, levelValue);
        }
        return experienceLevels;
    }

    public void saveExperienceLevels(ExperienceLevels experienceLevels) {
        String clearTableQuery = String.format("DELETE FROM %s;", EXPERIENCE_LEVELS_TABLE);
        dao.inputData(clearTableQuery);
        Map<String,Integer> levelsAndValues = experienceLevels.getLevels();
        if(levelsAndValues.size() > 0) {
            Set<String> levels = levelsAndValues.keySet();
            Integer[] values = levelsAndValues.values().toArray(new Integer[0]);
            int index = 0;
            for(String level : levels) {
                int value = values[index];
                String query = String.format("INSERT INTO %s VALUES(null, '%s', %s);",
                        EXPERIENCE_LEVELS_TABLE, level, value);
                dao.inputData(query);
                index++;
            }
        }
    }

    List<String> getStudentsSetsNames(String studentSetTable) {
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
