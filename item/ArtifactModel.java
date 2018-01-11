package item;

public class ArtifactModel extends ItemModel{
    private int price;

    public ArtifactModel(String itemName, String itemDescription, int price){
        super();
        this.name = itemName;
        this.description = itemDescription;
        this.price = price;
        this.genre = "artifact";
        this.type = 'B';
    }

    public ArtifactModel(int id, char itemType, String itemName, String itemDescription, int price){
        super(id);
        this.genre = "artifact";
        this.type = itemType;
        this.name = itemName;
        this.description = itemDescription;
        this.price = price;

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
