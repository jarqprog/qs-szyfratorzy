package model;

public class QuestFactoryImpl implements ItemFactory {

    public Quest create(String name, String description, int reward) {

        Quest quest = new Quest(name, description, reward);
        int id = ModelDaoFactory.getByType(QuestDAO.class).saveObjectAndGetId(quest);
        quest.setId(id);
        return quest;
    }
}
