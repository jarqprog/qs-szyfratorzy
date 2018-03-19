package model;

import dao.PassiveModelDAOImpl;
import managers.DbProcessManager;
import enums.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ExperienceLevelsDAO  extends PassiveModelDAOImpl<ExperienceLevels> {

    ExperienceLevelsDAO(Connection connection) {
        super(connection);
    }

    public HashMap<String, Integer> load() {
        String levelName;
        Integer levelValue;
        int LEVEL_NAME_INDEX = 0;
        int LEVEL_VALUE_INDEX = 1;
        HashMap<String, Integer> experienceLevels = new HashMap<>();
        List<String[]> dataCollection = getExpLevelsData();
        if (dataCollection.size() > 0) {
            for (String[] data : dataCollection) {
                levelName = data[LEVEL_NAME_INDEX];
                levelValue = Integer.parseInt(data[LEVEL_VALUE_INDEX]);
                experienceLevels.put(levelName, levelValue);
            }
        }
        return experienceLevels;
    }

    public boolean saveModel(ExperienceLevels experienceLevels) {

        Map<String,Integer> levelsAndValues = experienceLevels.getLevels();

        if(levelsAndValues.size() > 0) {
            try {
                clearExpLevels();
                String query = String.format("INSERT INTO %s VALUES(null, ?, ?) ", DEFAULT_TABLE);
                preparedStatement = connection.prepareStatement(query);
                Set<String> levels = levelsAndValues.keySet();
                Integer[] values = levelsAndValues.values().toArray(new Integer[0]);
                int index = 0;
                for(String level : levels) {
                    int value = values[index];
                    preparedStatement.setString(1, level);
                    preparedStatement.setInt(2, value);
                    preparedStatement.addBatch();
                    index++;
                }
                DbProcessManager.executeBatch(preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.EXPERIENCE_LEVELS.getName();
    }

    private List<String[]> getExpLevelsData() {
        String query = String.format("SELECT level_name, level_value FROM %s",
                DEFAULT_TABLE);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return DbProcessManager.getObjectsDataCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }   return new ArrayList<>();
    }

    private void clearExpLevels() throws SQLException {
        String clearQuery = String.format("DELETE FROM %s", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(clearQuery);
        DbProcessManager.executeUpdate(preparedStatement);
    }
}
