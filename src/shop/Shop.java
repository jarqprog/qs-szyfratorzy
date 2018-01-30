package shop;

import item.ArtifactModel;

import java.util.ArrayList;
import java.util.List;

public class Shop{
    List<ArtifactModel> store;

    public Shop() {
        store = new ArrayList<>();
        }

    public List<ArtifactModel> getStore() { return store;}

    public void setStore(List<ArtifactModel> store) {this.store = store;}

}
