package item;

import application.DAO;

public class ItemsDAO extends DAO
{

    public ItemsDAO(){
        defaultFileName = "maxItemsId.csv";
        defaultFilePath = "data_file/maxItemsId.csv";
        prepareFile();

    }
}
