package dao;


import managers.TemporaryManager;
import model.ExperienceLevels;
import enums.Table;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExperienceLevelsDAO  extends PassiveModelDAOImpl<ExperienceLevels> {

    private String DEFAULT_TABLE;
    private TemporaryManager dao;

    public ExperienceLevelsDAO(Connection connection) {
        super(connection);
        setDefaultTable();
        dao = new TemporaryManager();
    }

    public HashMap<String, Integer> load(){
        String levelName;
        Integer levelValue;
        int LEVEL_NAME_INDEX = 0;
        int LEVEL_VALUE_INDEX = 1;
        HashMap<String, Integer> experienceLevels = new HashMap<>();
        final String query = String.format("SELECT level_name, level_value FROM %s;", DEFAULT_TABLE);

        List<String[]> dataCollection = dao.getData(query);
        for(String[] data : dataCollection){
            levelName = data[LEVEL_NAME_INDEX];
            levelValue = Integer.parseInt(data[LEVEL_VALUE_INDEX]);
            experienceLevels.put(levelName, levelValue);
        }
        return experienceLevels;
    }

    public void save(ExperienceLevels experienceLevels) {
        String clearTableQuery = String.format("DELETE FROM %s;", DEFAULT_TABLE);
        dao.inputData(clearTableQuery);
        Map<String,Integer> levelsAndValues = experienceLevels.getLevels();
        if(levelsAndValues.size() > 0) {
            Set<String> levels = levelsAndValues.keySet();
            Integer[] values = levelsAndValues.values().toArray(new Integer[0]);
            int index = 0;
            for(String level : levels) {
                int value = values[index];
                String query = String.format("INSERT INTO %s VALUES(null, '%s', %s);",
                        DEFAULT_TABLE, level, value);
                dao.inputData(query);
                index++;
            }
        }
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.EXPERIENCE_LEVELS.getName();
    }
}
