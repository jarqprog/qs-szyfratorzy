package item;

import application.DataTool;


public abstract class ItemModel {

    protected char type;
    protected String name;
    protected String description;
    protected String genre;
    protected int id;

    public ItemModel(int id, String name, char type, String description){
        this(name, description);
        this.id = id;
        this.type = type;
        this.genre = "item";
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

    public void setName(String name){
        this.name = name;
        saveObject();
    }

    public char getType(){
        return type;
    }

    public int getId(){
        return id;
    }

    public void setType(char type){
        this.type = type;
        saveObject();
    }

    public String getDescription(){
        return description;
    }

    public String getShortDescription() {
        final int MAX_DESCRIPTION_LENGTH = 80;
        return DataTool.getShortText(MAX_DESCRIPTION_LENGTH, description);
    }

    public void setDescription(String description){
        this.description = description;
        saveObject();
    }

    public boolean compare(ItemModel item){
        if(item == null){
            return false;
        }
        if(item.getId() == this.id){
            return true;
        }
        return false;
    }

    public String getFullName(){
        return String.format("%s - type: (%s) name: %s", genre, type, name);
    }

    public String getGenre(){
        return genre;
    }

    public String toString(){
        return String.format("genre: %s\n, id: %s\n, type: %s\n, item name: %s\n, description: %s\n\n\n", this.genre, this.id,
                            this.type, this.name, this.description);
    }

    public abstract void saveObject();


}
