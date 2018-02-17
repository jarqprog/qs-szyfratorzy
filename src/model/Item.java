package model;

import tools.DataTool;


public abstract class Item extends ActiveObject {

    protected char type;
    protected String name;
    protected String description;
    protected String genre;

    public Item(int id, String name, char type, String description){
        this(name, description);
        this.id = id;
        this.type = type;
        this.genre = "item";
    }

    public Item(String name, String description){
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

    public void setType(char type){
        this.type = type;
        saveObject();
    }

    public String getDescription(){
        return description;
    }

    public String getShortDescription() {
        final int MAX_DESCRIPTION_LENGTH = 40;
        return DataTool.getShortText(MAX_DESCRIPTION_LENGTH, description);
    }

    public void setDescription(String description){
        this.description = description;
        saveObject();
    }

    public boolean compare(Item item){
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
        return String.format("id: %s, type: %s, name: %s, desc.: %s", id,
                            type, name, getShortDescription());
    }

    public String getFullDataToString() {
        return  String.format("\tid: %s\n\ttype: %s\n\tname: %s\n\tdescription: %s", id,
                type, name, description);
    }
}
