package item;

import application.AbstractView;

import java.util.List;

public class QuestView extends AbstractView {

    public void displayQuests(List<QuestModel> quests) {
        for (QuestModel singleQuest : quests) System.out.println(singleQuest.toString());
        }

    public Integer typeQuestIdInput(){
       Integer questID =  getNotNegativeNumberFromUser("Chose id to pick quest");
       return questID;
    }
}
