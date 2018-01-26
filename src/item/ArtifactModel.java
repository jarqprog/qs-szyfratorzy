package item;

public class ArtifactModel extends ItemModel{
    private int price;

    public ArtifactModel(int id, char type, String name, String description, int price){
        this(name, description, price);
        this.id = id;
        this.genre = "artifact";
        this.type = type;
    }

    public ArtifactModel(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
        this.genre = "artifact";
        this.type = 'B';
        ArtifactDAO dao = new ArtifactDAO();
        dao.saveObject(this);
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
