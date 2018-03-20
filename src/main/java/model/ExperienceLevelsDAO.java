package model;

import dao.PassiveModelDAOImpl;
import managers.SQLProcessManager;
import enums.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                String query = String.format("INSERT INTO %s VALUES(null, ?, ?) ", getDefaultTable());
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
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
                SQLProcessManager.executeBatch(preparedStatement, getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    protected void setDefaultTable(){
        setDefaultTable(Table.EXPERIENCE_LEVELS);
    }

    private List<String[]> getExpLevelsData() {
        String query = String.format("SELECT level_name, level_value FROM %s",
                getDefaultTable());
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SQLProcessManager.getObjectsDataCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }   return new ArrayList<>();
    }

    private void clearExpLevels() throws SQLException {
        String clearQuery = String.format("DELETE FROM %s", getDefaultTable());
        PreparedStatement preparedStatement = getConnection().prepareStatement(clearQuery);
        SQLProcessManager.executeUpdate(preparedStatement);
    }
}
