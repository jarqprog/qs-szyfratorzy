package item;

public abstract class ItemModel {
    protected char type;
    protected String name;
    protected String description;
    protected String fullName;
    protected String genre;
    protected int id;

    public ItemModel(){
        this.id = -1;
        this.name = "item";
        this.type = 'I';
        this.description = "n/a";
        this.genre = "item";
    }

    public ItemModel(int id){
        this.id = id;
        this.name = "item";
        this.type = 'I';
        this.description = "n/a";
        this.genre = "item";
    }

    public String getName(){
        return name;
    }
    public void setName(String newItemName){
        name = newItemName;
    }
    public char getType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public void setType(char newItemType){
        type = newItemType;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String newItemDescription){
        description = newItemDescription;
    }
    public String getFullName(){
        return fullName;
    }
    public String getGenre(){
        return genre;
    }
    public void setFullName(String newItemFullName){
        fullName = newItemFullName;
    }
    public String toString(){
        return String.format("genre: %s, id: %s, type: %s, item name: %s, description: %s", this.genre, this.id,
                            this.type, this.name, this.description);
    }

}
