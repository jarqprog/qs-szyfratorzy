package item;

public class QuestDAO extends ItemsDAO
{
    public QuestDAO(){
        defaultFileName = "quests.csv";
        defaultFilePath = "DataFiles/quests.csv";
        prepareFile();
    }

}
