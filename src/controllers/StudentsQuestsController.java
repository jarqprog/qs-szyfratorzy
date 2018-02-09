package controllers;

import java.util.List;

import Interface.CreatableDAO;
import dao.QuestDAO;
import view.QuestView;
import model.Quest;
import model.StudentsQuests;


public class StudentsQuestsController {

    QuestDAO questDAO;
    QuestView questView;
    CreatableDAO dao;


    public void addQuestToStudentsQuests(StudentsQuests studentsQuests){

        questDAO = new QuestDAO();
        questView = new QuestView();
        dao = new QuestDAO();

        List<Quest> quests= questDAO.getAllObjects();
        questView.displayQuests(quests);
        Integer questsId = questView.typeQuestIdInput();
        Quest pickedQuest = dao.getObjectById(questsId);
        studentsQuests.addItem(pickedQuest);
        }
    }
