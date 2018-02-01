package item;

<<<<<<< HEAD
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

=======
import java.util.List;
import java.util.ArrayList;

import application.DbManagerDAO;
import application.FactoryDAO;
import application.Table;

public class QuestDAO extends FactoryDAO {

    private final static int ID_INDEX = 0;
    private final static int NAME_INDEX = 1;
    private final static int TYPE_INDEX = 2;
    private final static int DESCRIPTION_INDEX = 3;
    private final static int REWARD_INDEX = 4;

    public QuestDAO(){
        this.DEFAULT_TABLE = Table.QUESTS.getName();
    }

    public List<QuestModel> getManyObjects(String query) {
        dao = new DbManagerDAO();
        List<String[]> dataCollection = dao.getData(query);
        return getManyObjects(dataCollection);
    }

    public List<QuestModel> getManyObjects(List<String[]> dataCollection) {
        ArrayList<QuestModel> quests = new ArrayList<>();
>>>>>>> 81f9d5b305039bc966563dd0f21b44d1dd88a3ac
        for (String [] record : dataCollection) {
            QuestModel quest = getOneObject(record);
            quests.add(quest);
        }
        return quests;
    }

<<<<<<< HEAD
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
=======
    public QuestModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }

    public QuestModel getOneObject(String[] record) {
        int id = Integer.parseInt(record[ID_INDEX]);
        char itemType = record[TYPE_INDEX].charAt(0);
        String itemName = record[NAME_INDEX];
        String itemDescription = record[DESCRIPTION_INDEX];
        int reward = Integer.parseInt(record[REWARD_INDEX]);
        return new QuestModel(id, itemType, itemName, itemDescription, reward);
    }

    public <T> void saveObject(T t){
        QuestModel quest = (QuestModel) t;
        String itemId = String.valueOf(quest.getId());
        String itemType = String.valueOf(quest.getType());
        String itemName = quest.getName();
        String itemDescription = quest.getDescription();
        String reward = String.valueOf(quest.getReward());

        String query;
        if (itemId.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, itemName, itemType, itemDescription, reward);
        } else {
            query = String.format("UPDATE %s SET name='%s' , type='%s', description='%s', reward=%s " +
                    "WHERE id=%s;", DEFAULT_TABLE, itemName, itemType, itemDescription, reward, itemId);
        }
        DbManagerDAO dao = new DbManagerDAO();
        dao.inputData(query);
    }
}
>>>>>>> 81f9d5b305039bc966563dd0f21b44d1dd88a3ac
