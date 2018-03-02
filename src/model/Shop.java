package model;

import dao.ArtifactDAO;
import dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    List<Artifact> store;

    public Shop() {
        store = new ArrayList<>();
        }

    public List<Artifact> getStore() {
        store = DaoFactory.getByType(ArtifactDAO.class).getAllObjects();
        return store;
    }

    public void setStore(List<Artifact> store) {this.store = store;}

}
