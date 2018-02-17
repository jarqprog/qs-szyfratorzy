package model;

import enums.QuestsStatus;

public class Quest extends Item {

    private int reward;
    private String status;

    public Quest(String name, String description, int reward){
        super(name, description);
        this.reward = reward;
        this.type = 'B';
        this.status = QuestsStatus.AVAILABLE.getName();
    }

    public Quest(int id, char type, String name, String description, int reward, String status){
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
        return String.format(super.toString() + " reward: %d, status: %s", reward, status);
    }

    public String getFullDataToString() {
        return  String.format(super.getFullDataToString() + "\n\treward: %s\n\tstatus: %s\n", reward, status);
    }
}
