package model;

public class Artifact extends Item {

    private int price;

    Artifact(String name, String description, int price){
        super(name, description);
        this.price = price;
        this.genre = "artifact";
        this.type = 'B';
    }

    public Artifact(int id, char type, String name, String description, int price){
        super(name, description);
        this.id = id;
        this.type = type;
        this.price = price;
        this.genre = "artifact";
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int newPrice){
        price = newPrice;
        saveObject();
    }

    public String toString(){
        return super.toString() + String.format(" price: %s", getPrice());
    }

    public String getFullDataToString() {
        return  String.format(super.getFullDataToString() + "\n\tprice: %s\n", price);
    }
}
