package controllers;

import java.util.List;

import dao.ActiveModelDAO;
import dao.QuestDAO;
import model.Quest;
import model.Student;
import view.UsersView;


public class StudentsQuestsController {

    UsersView view;
    ActiveModelDAO<Quest> dao;

    public void runQuestMenu(Student student){

        view = new UsersView();
        dao = new QuestDAO();
        List<Quest> quests = dao.getAllObjects();
        view.displayObjects(quests);
        Integer questsId = view.getNotNegativeNumberFromUser("Choose id to pick quest: ");
        Quest pickedQuest = dao.getObjectById(questsId);
        student.getStudentsQuests().addItem(pickedQuest);
        view.clearScreen();
        view.displayMessageInNextLine("You have taken up the task:\n");
        view.displayObject(pickedQuest);
        }
    }
