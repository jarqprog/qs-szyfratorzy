package model;

import dao.ActiveModelDAO;
import dao.ArtifactDAO;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    List<Artifact> store;

    public Shop() {
        store = new ArrayList<>();
        }

    public List<Artifact> getStore() {
        ActiveModelDAO<Artifact> dao = new ArtifactDAO();
        store = dao.getAllObjects();
        return store;
    }

    public void setStore(List<Artifact> store) {this.store = store;}

}
