package shop;

import application.CreatableDAO;
import dao.ArtifactDAO;
import item.ArtifactModel;

import java.util.ArrayList;
import java.util.List;

public class ShopModel {
    List<ArtifactModel> store;

    public ShopModel() {
        store = new ArrayList<>();
        }

    public List<ArtifactModel> getStore() {
        CreatableDAO dao = new ArtifactDAO();
        store = dao.getAllObjects();
        return store;
    }

    public void setStore(List<ArtifactModel> store) {this.store = store;}

}
