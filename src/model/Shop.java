package model;

import Interface.CreatableDAO;
import dao.ArtifactDAO;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    List<Artifact> store;

    public Shop() {
        store = new ArrayList<>();
        }

    public List<Artifact> getStore() {
        CreatableDAO dao = new ArtifactDAO();
        store = dao.getAllObjects();
        return store;
    }

    public void setStore(List<Artifact> store) {this.store = store;}

}
