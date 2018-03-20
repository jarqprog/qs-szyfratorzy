package model;

public class Artifact extends Item {

    private int price;

    Artifact(String name, String description, int price){
        super(name, description);
        this.price = price;
        setGenre("artifact");
    }

    Artifact(int id, char type, String name, String description, int price){
        super(id, name, type, description);
        this.price = price;
        setGenre("artifact");

    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int newPrice){
        this.price = newPrice;
        saveModel();
    }

    public String toString(){
        return super.toString() + String.format(" price: %s", price);
    }

    public String getFullDataToString() {
        return  String.format(super.getFullDataToString() + "\n\tprice: %s\n", price);
    }
}
