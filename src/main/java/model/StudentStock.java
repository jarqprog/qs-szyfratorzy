package model;

public abstract class StudentStock extends PassiveModel {

    StudentStock(int ownerId) {
        this.ownerId = ownerId;
    }

}
