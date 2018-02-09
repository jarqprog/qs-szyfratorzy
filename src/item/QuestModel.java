package item;

import application.QuestsStatus;
import dao.QuestDAO;

public class QuestModel extends ItemModel {

    private int reward;
    private String status;

    public QuestModel(String name, String description, int reward){
        super(name, description);
        this.id = -1;
        this.reward = reward;
        this.type = 'B';
        this.status = QuestsStatus.AVAILABLE.getName();
        this.id = saveNewObjectGetId();
    }

    public QuestModel(int id, char type, String name, String description, int reward, String status){
        super(name, description);
        this.id = id;
        this.type = type;
        this.reward = reward;
        this.status = status;

    }

    public int getReward(){
        return reward;
    }

    public void setReward(int reward){
        this.reward = reward;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String toString() {
        return String.format("\n id: %d  name: %s\n description: %s\n reward: %d type: %s status: %s\n\n",
                            id, name,description, reward, type, status);
    }


    public int saveNewObjectGetId(){
        QuestDAO dao = new QuestDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void saveObject(){
        QuestDAO dao = new QuestDAO();
        dao.saveObject(this);
    }
}
