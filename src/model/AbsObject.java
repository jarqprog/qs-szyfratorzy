package model;

import factory.DaoFactory;
import view.UsersView;

public abstract class AbsObject {

    @SuppressWarnings("unchecked")
    public void saveObject() {

        UsersView view = new UsersView();
        view.displayMessage("Jestem w save Object: " + getClass().getSimpleName());
        view.displayObject(this);
        view.handlePause();

        DaoFactory.get(getClass().getSimpleName()).save(this);
    }
}
