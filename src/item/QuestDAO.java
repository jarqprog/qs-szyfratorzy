package item;

import java.util.ArrayList;
import java.util.List;
import application.Table;
import application.DbManagerDAO;


public class QuestDAO extends ItemsDAO {

    private DbManagerDAO daoManager;

    private final String DEFAULT_TABLE = Table.GROUPS.getName();
    private final Integer ID_INDEX = 0;
    private final Integer NAME_INDEX = 1;
    private final Integer TYPE_INDEX = 2;
    private final Integer DESCRIPTION_INDEX = 3;
    private final Integer REWARD_INDEX = 4;

    private int questId;
    private String name;
    private char type;
    private String description;
    private int reward;

    public List<QuestModel> getManyObjects(List<String[]> dataCollection) {

        ArrayList<QuestModel> quests = new ArrayList<QuestModel>();

        for (String [] record : dataCollection) {
            QuestModel quest = getOneObject(record);
            quests.add(quest);
        }
        return quests;
    }

    public List<QuestModel> getManyObjects(String query) {
        daoManager = new DbManagerDAO();
        List<String[]> dataCollection = daoManager.getData(query);
        List<QuestModel> quests = new ArrayList<QuestModel>();
        for (String[] record : dataCollection) {
            QuestModel quest = getOneObject(record);
            quests.add(quest);
        }
        return quests;
    }

    public QuestModel getOneObject(String query) {
        daoManager = new DbManagerDAO();
        String[] questData = daoManager.getData(query).get(0);
        questId = Integer.parseInt(questData[ID_INDEX]);
        name = questData[NAME_INDEX];
        type = questData[TYPE_INDEX].charAt(0);
        description = questData[DESCRIPTION_INDEX];
        reward = Integer.parseInt(questData[REWARD_INDEX]);        

        return new QuestModel(questId, name, type, description, reward);
    }

    public QuestModel getOneObject(String[] record) {
        questId = Integer.parseInt(record[ID_INDEX]);
        name = record[NAME_INDEX];
        type = record[TYPE_INDEX].charAt(0);
        description = record[DESCRIPTION_INDEX];
        reward = Integer.parseInt(record[REWARD_INDEX]);

        return new QuestModel(questId, name, type, description, reward);
        }

    public void saveObject(QuestModel quest) {
        String questId = String.valueOf(quest.getId());
        name = quest.getName();
        type = quest.getType();
        description = quest.getDescription();
        reward = quest.getReward();

        String query;
        if(questId.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s VALUES(null, '%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, name, type, description, reward);
        } else {
            query = String.format("UPDATE %s SET name='%s' , type='%s', description='%s', reward='%s' " +
                    "WHERE id=%s;", DEFAULT_TABLE, name, type, description, reward, questId);
        }
        daoManager = new DbManagerDAO();
        daoManager.inputData(query);
    }

    public void saveObjects(List<QuestModel> quests) {

        for(QuestModel quest : quests) {
            saveObject(quest);
        }
    }


}