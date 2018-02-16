package controllers;

import java.util.List;

import dao.ActiveObjDAO;
import dao.QuestDAO;
import view.QuestView;
import model.Quest;
import model.Student;


public class StudentsQuestsController {

    QuestView view;
    ActiveObjDAO<Quest> dao;


    public void runQuestMenu(Student student){

        view = new QuestView();
        dao = new QuestDAO();

        List<Quest> quests = dao.getAllObjects();
        view.displayQuests(quests);
        Integer questsId = view.typeQuestIdInput();
        Quest pickedQuest = dao.getObjectById(questsId);
        student.getStudentsQuests().addItem(pickedQuest);
        view.clearScreen();
        view.displayMessage("\nYou have taken up the task:\n");
        view.displayObject(pickedQuest);
        }
    }
