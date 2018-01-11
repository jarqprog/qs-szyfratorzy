package item;

class ArtefactModel extends ItemModel{
    private int price ;

    public ArtefactModel(char itemType, String itemName, String itemDescription, int price){
        super(itemType, itemName, itemDescription);
        this.price = price;

    }

    public static void main(String[] args){
        ArtefactModel dupa = new ArtefactModel('M', "Magiczna Pałka", "jest magiczna", 1500);
        System.out.println(dupa.getItemName());
        System.out.println(dupa.getItemDescription());
        System.out.println(dupa.price);

    }
}
