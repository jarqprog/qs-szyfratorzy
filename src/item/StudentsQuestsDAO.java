package item;

import application.CreatableDAO;
import application.DbManagerDAO;
import application.Table;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentsQuestsDAO {

    private String STUDENTS_QUESTS_TABLE;
    private CreatableDAO questDao;
    private DbManagerDAO dataBaseDao;

    public StudentsQuestsDAO() {

        STUDENTS_QUESTS_TABLE = Table.STUDENTS_QUESTS.getName();
        questDao = new QuestDAO();
        dataBaseDao = new DbManagerDAO();

    }

    Map<QuestModel,LocalDate> loadQuests(int ownerId) {

        QuestModel quest;
        LocalDate date;
        int QUEST_ID_INDEX = 0;
        int DATE_INDEX = 1;
        Map<QuestModel,LocalDate> questsStock = new HashMap<>();
        final String query = String.format("SELECT quests_id, date FROM %s WHERE owner_id=%s;",
                STUDENTS_QUESTS_TABLE, ownerId);
        List<String[]> dataCollection = dataBaseDao.getData(query);
        for(String[] data : dataCollection){
            int questId = Integer.parseInt(data[QUEST_ID_INDEX]);
            quest = questDao.getObjectById(questId);
            date = LocalDate.parse(data[DATE_INDEX]);
            questsStock.put(quest, date);
        }
        return questsStock;
    }

    public void saveQuests(StudentsQuestsModel studentsQuests) {
        int ownerId = studentsQuests.getOwnerId();
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=%s;",
                STUDENTS_QUESTS_TABLE, ownerId);
        dataBaseDao.inputData(clearQuery);
        Map<QuestModel,LocalDate> questsStock  = studentsQuests.getStock();
        if(questsStock.size() > 0) {
            Set<? extends ItemModel> quests = questsStock.keySet();
            Object[] dates = questsStock.values().toArray(new Object[0]);
            String date;
            int questId;
            int index = 0;
            for(ItemModel quest : quests) {
                questId = quest.getId();
                LocalDate localDate = (LocalDate) dates[index];
                date = localDate.toString();
                String query = String.format("INSERT INTO %s VALUES(null, '%s', %s, %s);",
                        STUDENTS_QUESTS_TABLE, ownerId, questId, date);
                dataBaseDao.inputData(query);
                index++;
            }
        }
    }
}
