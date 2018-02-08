package application;

public abstract class StudentStockModel {

    protected int ownerId;

    public StudentStockModel(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

}
