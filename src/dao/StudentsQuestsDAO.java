package dao;

import enums.Table;
import managers.DbProcessManager;
import model.Quest;
import model.StudentsQuests;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentsQuestsDAO extends PassiveModelDAOImpl<StudentsQuests> {

    StudentsQuestsDAO(Connection connection) {
        super(connection);
    }

    public Map<Quest,LocalDate> load(int ownerId) throws SQLException {
        Quest quest;
        LocalDate date;
        int QUEST_ID_INDEX = 0;
        int DATE_INDEX = 1;
        Map<Quest,LocalDate> questsStock = new HashMap<>();
        List<String[]> dataCollection = getQuestStockData(ownerId);
        if (dataCollection != null) {
            for (String[] data : dataCollection) {
                int questId = Integer.parseInt(data[QUEST_ID_INDEX]);
                quest = DaoFactory.getByType(QuestDAO.class).getModelById(questId);
                date = LocalDate.parse(data[DATE_INDEX]);
                questsStock.put(quest, date);
            }
        }
        return questsStock;
    }

    public boolean save(StudentsQuests studentsQuests) {

        Map<Quest,LocalDate> questsStock  = studentsQuests.getStock();

        if(questsStock.size() > 0) {
            int ownerId = studentsQuests.getOwnerId();

            try {
                clearQuests(ownerId);
                String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?)", DEFAULT_TABLE);
                preparedStatement = connection.prepareStatement(query);
                Set<Quest> quests = questsStock.keySet();
                LocalDate[] dates = questsStock.values().toArray(new LocalDate[0]);
                String date;
                int questId;
                int index = 0;
                for(Quest quest : quests) {
                    questId = quest.getId();
                    LocalDate localDate = dates[index];
                    date = localDate.toString();
                    preparedStatement.setInt(1, ownerId);
                    preparedStatement.setInt(2, questId);
                    preparedStatement.setString(3, date);
                    preparedStatement.addBatch();
                    index++;
                }
                DbProcessManager.executeBatch(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.STUDENTS_QUESTS.getName();
    }

    private List<String[]> getQuestStockData(int ownerId) throws SQLException {
        String query = String.format("SELECT quests_id, date FROM %s WHERE owner_id=?",
                DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ownerId);
        resultSet = preparedStatement.executeQuery();
        return DbProcessManager.getObjectsDataCollection(resultSet);
    }

    private void clearQuests(int ownerId) throws SQLException {
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=?", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(clearQuery);
        preparedStatement.setInt(1, ownerId);
        DbProcessManager.executeUpdate(preparedStatement);
    }
}
