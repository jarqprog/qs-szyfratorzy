package model;

public abstract class ActiveModel extends CommonModel {

    protected int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
