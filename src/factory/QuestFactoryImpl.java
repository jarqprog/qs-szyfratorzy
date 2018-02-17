package factory;

import dao.QuestDAO;
import model.Quest;

public class QuestFactoryImpl implements ItemFactory {

    public Quest create(String name, String description, int reward) {

        Quest quest = new Quest(name, description, reward);
        int id = new QuestDAO().saveObjectAndGetId(quest);
        quest.setId(id);
        return quest;
    }
}
