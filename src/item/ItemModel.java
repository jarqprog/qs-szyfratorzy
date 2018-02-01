package item;

public abstract class ItemModel {

    protected char type;
    protected String name;
    protected String description;
    protected String genre;
    protected int id;

<<<<<<< HEAD
    public ItemModel(){
        dao = new ItemsDAO();
        counter = dao.loadLastId("data_files/maxItemsId.csv");
        id = counter++;
        this.name = "item";
        this.type = 'I';
        this.description = "n/a";
        this.genre = "item";
        dao.saveLastId(counter, "data_files/maxItemsId.csv");
=======
    public ItemModel(int id, String name, char type, String description){
        this(name, description);
        this.id = id;
        this.type = type;
        this.genre = "item";
>>>>>>> 81f9d5b305039bc966563dd0f21b44d1dd88a3ac
    }

    public ItemModel(String name, String description){
        this.id = -1;
        this.name = name;
        this.type = 'I';
        this.description = description;
        this.genre = "item";
    }

    public String getName(){
        return name;
    }

    public void setName(String newItemName){
        name = newItemName;
        saveObject();
    }

    public char getType(){
        return type;
    }

    public int getId(){
        return id;
    }

    public void setType(char newItemType){
        type = newItemType;
        saveObject();
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String newItemDescription){
        description = newItemDescription;
        saveObject();
    }

    public String getFullName(){
        return String.format("%s - type: (%s) name: %s", genre, type, name);
    }

    public String getGenre(){
        return genre;
    }

    public String toString(){
        return String.format("genre: %s, id: %s, type: %s, item name: %s, description: %s", this.genre, this.id,
                            this.type, this.name, this.description);
    }

    public abstract void saveObject();

}
