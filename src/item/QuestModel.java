package item;

public class QuestModel extends ItemModel {

    private int reward;

    public QuestModel(String name, String description, int reward){
        super(name, description);
        this.id = -1;
        this.reward = reward;
        this.genre = "quest";
        this.type = 'R';
        QuestDAO dao = new QuestDAO();
        dao.saveObject(this);
    }

    public QuestModel(int id, char type, String name, String description, int reward){
        super(name, description);
        this.id = id;
        this.type = type;
        this.reward = reward;
        this.genre = "quest";
    }

    public int getReward(){
        return reward;
    }

    public void setReward(int reward){
        this.reward = reward;
    }

    public String toString(){
        return super.toString() + String.format(" reward: %s", getReward());
    }
}
