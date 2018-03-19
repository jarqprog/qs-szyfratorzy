package model;

import tools.DataTool;


public abstract class Item extends ActiveModel {

    private char type;
    private String name;
    private String description;
    private String genre;

    Item(int id, String name, char type, String description){
        this(name, description);
        setId(id);
        this.type = type;
    }

    Item(String name, String description){
        setId(-1);
        this.name = name;
        this.description = description;
        this.type = 'B';
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        saveModel();
    }

    public char getType(){
        return type;
    }

    public void setType(char type){
        this.type = type;
        saveModel();
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
        saveModel();
    }

    public boolean compare(Item item){
        if(item == null){
            return false;
        }
        if(item.getId() == getId()){
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
        return String.format("id: %s, type: %s, name: %s, desc.: %s", getId(),
                            type, name, getShortDescription());
    }

    public String getFullDataToString() {
        return  String.format("\tid: %s\n\ttype: %s\n\tname: %s\n\tdescription: %s", getId(),
                type, name, description);
    }

    protected void setGenre(String genre) {
        this.genre = genre;
    }
}
