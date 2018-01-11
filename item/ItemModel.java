package item;

public abstract class ItemModel {
    private static int counter;
    private int id;
    private char itemType;
    private String itemName;
    private String itemDescription;
    private String itemFullName;
    private ItemsDAO itemsDAO;

    public ItemModel(char itemType, String itemName, String itemDescription){
        itemsDAO = new ItemsDAO();
        counter = itemsDAO.loadLastId("DataFiles/maxItemId.csv");
        id = counter++;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        itemsDAO.saveLastId(counter, "DataFiles/maxItemId.csv");
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
        return String.format("%s %s",this.itemType, this.itemName);
    }
    public static void main(String[] args) {
    }

}
