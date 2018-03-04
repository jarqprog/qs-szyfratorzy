package dao;

import enums.Table;
import managers.DbProcessManager;
import model.Quest;

import java.sql.Connection;
import java.sql.SQLException;


public class QuestDAO extends ActiveModelDAOImpl<Quest> {

    QuestDAO(Connection connection) {
        super(connection);
    }

    public Quest extractModel(String[] record) {

        final int ID_INDEX = 0;
        final int NAME_INDEX = 1;
        final int TYPE_INDEX = 2;
        final int DESCRIPTION_INDEX = 3;
        final int REWARD_INDEX = 4;
        final int STATUS_INDEX = 5;

        int id = Integer.parseInt(record[ID_INDEX]);
        char itemType = record[TYPE_INDEX].charAt(0);
        String itemName = record[NAME_INDEX];
        String itemDescription = record[DESCRIPTION_INDEX];
        int reward = Integer.parseInt(record[REWARD_INDEX]);
        String status = record[STATUS_INDEX];
        return new Quest(id, itemType, itemName, itemDescription, reward, status);
    }

    public boolean save(Quest quest){
        String id = String.valueOf(quest.getId());
        String type = String.valueOf(quest.getType());
        String name = quest.getName();
        String description = quest.getDescription();
        int reward = quest.getReward();
        String status = quest.getStatus();
        String query;

        if (id.equals("-1")) {
            query = String.format(
                    "INSERT INTO %s VALUES(null, ?, ?, ?, ?, ?)", DEFAULT_TABLE);
        } else {
            query = String.format("UPDATE %s SET name=? , type=?, description=?, reward=? , status=? " +
                    "WHERE id=?", DEFAULT_TABLE);
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, reward);
            preparedStatement.setString(5, status);
            if(!id.equals("-1")) {
                preparedStatement.setInt(6, Integer.valueOf(id));
            }
            DbProcessManager.executeUpdate(preparedStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
}

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.QUESTS.getName();
    }
}
