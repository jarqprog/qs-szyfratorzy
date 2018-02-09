package view;

import model.Quest;

import java.util.List;

public class QuestView extends AbstractView {

    public void displayQuests(List<Quest> quests) {
        for (Quest singleQuest : quests) System.out.println(singleQuest.toString()); }

    public int typeQuestIdInput(){
       return getNotNegativeNumberFromUser("\nChoose id to pick quest: ");
    }
}
