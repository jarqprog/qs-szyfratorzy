package view;

import model.Quest;

import java.util.List;

public class QuestView extends AbstractView {

    public void displayQuests(List<Quest> quests) {
        for (Quest singleQuest : quests) System.out.println(singleQuest.toString());
        }

    public Integer typeQuestIdInput(){
       return getNotNegativeNumberFromUser("Chose id to pick quest");
    }
}
