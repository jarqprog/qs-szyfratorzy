package item;

public class QuestModel extends ItemModel{
    private int reward;

    public QuestModel(String itemName, char type, String itemDescription, int reward){
        super();
        this.name = itemName;
        this.type = type;
        this.description = itemDescription;
        this.reward = reward;
        this.genre = "quest";
    }

    public QuestModel(int id, String itemName, char itemType, String itemDescription, int reward){
        this.name = itemName;
        this.type = itemType;
        this.description = itemDescription;
        this.reward = reward;
        this.genre = "quest";
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public char getType(){
        return type;
    }

    public void setType(char type){
        this.type = type;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getReward(){
        return reward;
    }

    public void setReward(int reward){
        this.reward = reward;
    }

    public String toString(){
        return super.toString() + String.format(" price: %s", getReward());
    }
}
