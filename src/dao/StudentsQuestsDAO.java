package dao;

import enums.Table;
import managers.ResultSetManager;
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

    private ResultSetManager dao;

    public StudentsQuestsDAO(Connection connection) {
        super(connection);
        dao = new ResultSetManager();
    }

    public Map<Quest,LocalDate> load(int ownerId) throws SQLException {
        ActiveModelDAO<Quest> questDao = new QuestDAO(connection);
        Quest quest;
        LocalDate date;
        int QUEST_ID_INDEX = 0;
        int DATE_INDEX = 1;
        Map<Quest,LocalDate> questsStock = new HashMap<>();
        List<String[]> dataCollection = getQuestStockData(ownerId);
        if (dataCollection != null) {
            for (String[] data : dataCollection) {
                int questId = Integer.parseInt(data[QUEST_ID_INDEX]);
                quest = questDao.getModelById(questId);
                date = LocalDate.parse(data[DATE_INDEX]);
                questsStock.put(quest, date);
            }
        }
        return questsStock;
    }

    public void save(StudentsQuests studentsQuests) {
        int ownerId = studentsQuests.getOwnerId();
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=%s;",
                DEFAULT_TABLE, ownerId);
        Map<Quest,LocalDate> questsStock  = studentsQuests.getStock();
        dao.inputData(clearQuery);
        if(questsStock.size() > 0) {
            Set<Quest> quests = questsStock.keySet();
            LocalDate[] dates = questsStock.values().toArray(new LocalDate[0]);
            String date;
            int questId;
            int index = 0;
            for(Quest quest : quests) {
                questId = quest.getId();
                LocalDate localDate = dates[index];
                date = localDate.toString();
                String query = String.format("INSERT INTO %s VALUES(null, %s, %s, '%s');",
                        DEFAULT_TABLE, ownerId, questId, date);
                dao.inputData(query);
                index++;
            }
        }
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
        return ResultSetManager.getObjectsDataCollection(resultSet);
    }
}
