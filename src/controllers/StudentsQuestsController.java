package controllers;

import java.util.List;

import Interface.CreatableDAO;
import dao.QuestDAO;
import view.QuestView;
import model.Quest;
import model.Student;


public class StudentsQuestsController {

    QuestView view;
    CreatableDAO dao;


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
