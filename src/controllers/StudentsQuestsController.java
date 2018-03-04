package controllers;

import java.util.List;

import dao.DaoFactory;
import dao.QuestDAO;
import model.Quest;
import model.Student;
import view.UsersView;


public class StudentsQuestsController {

    public void runQuestMenu(Student student) {
        UsersView view;
        view = new UsersView();
        List<Quest> quests = DaoFactory.getByType(QuestDAO.class).getAllModels();
        view.displayObjects(quests);
        Integer questsId = view.getNotNegativeNumberFromUser("Choose id to pick quest: ");
        Quest pickedQuest = DaoFactory.getByType(QuestDAO.class).getModelById(questsId);
        view.clearScreen();
        if (!student.getStudentsQuests().containsQuest(pickedQuest)) {
            student.getStudentsQuests().addItem(pickedQuest);
            view.displayMessageInNextLine("You have taken up the task:\n");
            view.displayObject(pickedQuest);
        } else {
            view.displayMessageInNextLine("You already have this quest!");
        }
    }
}
