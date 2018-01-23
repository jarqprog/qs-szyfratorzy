package item;

import application.DAO;

public class ItemsDAO extends DAO
{

    public ItemsDAO(){
        defaultFileName = "maxItemsId.csv";
        defaultFilePath = "DataFiles/maxItemsId.csv";
        prepareFile();

    }
}
