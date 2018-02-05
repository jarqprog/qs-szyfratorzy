package item;

public class ArtifactModel extends ItemModel{

    private int price;

    public ArtifactModel(String name, String description, int price){
        super(name, description);
        this.id = -1;
        this.price = price;
        this.genre = "artifact";
        this.type = 'B';
        this.id = saveNewObjectGetId();
    }

    public ArtifactModel(int id, char type, String name, String description, int price){
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

    public int saveNewObjectGetId(){
        ArtifactDAO dao = new ArtifactDAO();
        return dao.saveObjectAndGetId(this);
    }

    public void saveObject(){
        ArtifactDAO dao = new ArtifactDAO();
        dao.saveObject(this);
    }
}
