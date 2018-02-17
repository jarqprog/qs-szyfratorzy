package model;

public abstract class StudentStock { //extends PassiveObject {

    protected int ownerId;

    public StudentStock(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

}
