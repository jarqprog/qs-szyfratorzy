package model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    List<Artifact> store;

    public Shop() {
        store = new ArrayList<>();
        }

    public List<Artifact> getStore() {
        return ModelDaoFactory.getByType(ArtifactDAO.class).getAllModels();
    }

    public void setStore(List<Artifact> store) {this.store = store;}

}
