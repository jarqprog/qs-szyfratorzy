package shop;

import item.ArtifactModel;

import java.util.ArrayList;
import java.util.List;

public class ShopModel {
    List<ArtifactModel> store;

    public ShopModel() {
        store = new ArrayList<>();
        }

    public List<ArtifactModel> getStore() { return store;}

    public void setStore(List<ArtifactModel> store) {this.store = store;}

}
