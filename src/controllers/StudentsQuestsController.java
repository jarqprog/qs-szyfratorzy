package controllers;

import java.sql.SQLException;
import java.util.List;

import dao.ActiveModelDAO;
import dao.DaoFactory;
import dao.QuestDAO;
import model.Quest;
import model.Student;
import view.UsersView;


public class StudentsQuestsController {

    public void runQuestMenu(Student student) throws SQLException {
        UsersView view;
        ActiveModelDAO<Quest> dao;
        view = new UsersView();
        dao = DaoFactory.getByType(QuestDAO.class);
        List<Quest> quests = dao.getAllObjects();
        view.displayObjects(quests);
        Integer questsId = view.getNotNegativeNumberFromUser("Choose id to pick quest: ");
        Quest pickedQuest = dao.getModelById(questsId);
        student.getStudentsQuests().addItem(pickedQuest);
        view.clearScreen();
        view.displayMessageInNextLine("You have taken up the task:\n");
        view.displayObject(pickedQuest);
        }
    }
