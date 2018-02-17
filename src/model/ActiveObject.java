package model;

public abstract class ActiveObject extends AbsObject {

    protected int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
