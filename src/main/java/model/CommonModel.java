package model;

public abstract class CommonModel {
    // parent class of all BO in app

    public void saveModel() {
        String className = getClass().getSimpleName();
        ModelDaoFactory.getByModel(className).saveModel(this);
    }
}
