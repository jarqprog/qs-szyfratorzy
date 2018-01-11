package item;

public class ArtifactModel extends ItemModel{
    private int price;

    public ArtifactModel(char itemType, String itemName, String itemDescription, int price){
        super(itemType, itemName, itemDescription);
        this.price = price;
        this.genre = "artifact";
    }

    public ArtifactModel(int id, char itemType, String itemName, String itemDescription, int price){
        super(id, itemType, itemName, itemDescription);
        this.id = id;
        this.price = price;
        this.genre = "artifact";
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int newPrice){
        price = newPrice;
    }

    public String toString(){
        return super.toString() + String.format(" price: %s", getPrice());
    }
}
