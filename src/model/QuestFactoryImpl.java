package model;

import dao.QuestDAO;

public class QuestFactoryImpl implements ItemFactory {

    public Quest create(String name, String description, int reward) {

        Quest quest = new Quest(name, description, reward);
        int id = new QuestDAO().saveObjectAndGetId(quest);
        quest.setId(id);
        return quest;
    }
}
