package model;

public abstract class StudentStock extends PassiveModel {

    StudentStock(int ownerId) {
        setOwnerId(ownerId);
    }

}
