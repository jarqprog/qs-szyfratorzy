package item;

public class ArtifactModel extends ItemModel{
    private int price ;

    public ArtifactModel(char itemType, String itemName, String itemDescription, int price){
        super(itemType, itemName, itemDescription);
        this.price = price;

    }

    // public static void main(String[] args){
    //     ArtifactModel dupa = new ArtifactModel('M', "Magiczna Pałka", "jest magiczna", 1500);
    //     System.out.println(dupa.getItemName());
    //     System.out.println(dupa.getItemDescription());
    //     System.out.println(dupa.price);
    //
    // }
}
