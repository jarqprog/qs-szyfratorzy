package item;

public abstract class ItemModel {
    protected char type;
    protected String name;
    protected String description;
    protected String fullName;
    protected String genre;
    protected static int counter;
    protected int id;
    protected ItemsDAO dao;

    public ItemModel(){
        dao = new ItemsDAO();
        counter = dao.loadLastId("DataFiles/maxItemsId.csv");
        id = counter++;
        this.name = "item";
        this.type = 'I';
        this.description = "n/a";
        this.genre = "item";
        dao.saveLastId(counter, "DataFiles/maxItemsId.csv");
    }

    public ItemModel(int id){
        this.id = id;
        this.name = "item";
        this.type = 'I';
        this.description = "n/a";
        this.genre = "item";
    }

    public String getItemName(){
        return name;
    }
    public void setItemName(String newItemName){
        name = newItemName;
    }
    public char getItemType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public void setItemtype(char newItemType){
        type = newItemType;
    }
    public String getItemDescription(){
        return description;
    }
    public void setItemDescription(String newItemDescription){
        description = newItemDescription;
    }
    public String getItemFullName(){
        return fullName;
    }
    public String getGenre(){
        return genre;
    }
    public void setItemFullName(String newItemFullName){
        fullName = newItemFullName;
    }
    public String toString(){
        return String.format("genre: %s, type: %s, item name: %s, description: %s", this.genre,
                            this.type, this.name, this.description);
    }

}
