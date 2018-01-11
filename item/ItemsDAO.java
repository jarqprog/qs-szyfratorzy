package item;

import application.AbstractDAO;

public class ItemsDAO extends AbstractDAO
{

    public ItemsDAO(){
        defaultFileName = "maxItemsId.csv";
        defaultFilePath = "DataFiles/maxItemsId.csv";
        prepareFile();

    }
}
