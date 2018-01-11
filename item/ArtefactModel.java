package item;

public class ArtefactModel extends ItemModel{
    private int price ;

    public ArtefactModel(char itemType, String itemName, String itemDescription, int price){
        super(itemType, itemName, itemDescription);
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
