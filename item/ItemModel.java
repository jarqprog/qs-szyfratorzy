package item;

public abstract class ItemModel {
    private static int counter;
    private int id;
    private char itemType;
    private String itemName;
    private String itemDescription;
    private String itemFullName;
    protected String genre;
    private static int counter;
    protected int id;
    private ItemsDAO dao;

    public ItemModel(char itemType, String itemName, String itemDescription){
        dao = new ItemsDAO();
        counter = dao.loadLastId("DataFiles/maxItemsId.csv");
        id = counter++;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.genre = "item";
    }

    public ItemModel(int id, char itemType, String itemName, String itemDescription){
        this.id = id;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.genre = "item";
    }

    public String getItemName(){
        return itemName;
    }
    public void setItemName(String newItemName){
        itemName = newItemName;
    }
    public char getItemType(){
        return itemType;
    }

    // public ing getId(){
    //
    // }
    public void setItemtype(char newItemType){
        itemType = newItemType;
    }
    public String getItemDescription(){
        return itemDescription;
    }
    public void setItemDescription(String newItemDescription){
        itemDescription = newItemDescription;
    }
    public String getItemFullName(){
        return itemFullName;
    }
    public void setItemFullName(String newItemFullName){
        itemFullName = newItemFullName;
    }
    public String toString(){
        return String.format("type: %s, item name: %s,", this.itemType, this.itemName);
    }

}
