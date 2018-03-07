package controllers;

import java.util.List;

import model.ModelDaoFactory;
import model.QuestDAO;
import model.Quest;
import model.Student;
import view.UsersView;


public class StudentsQuestsController {

    public void runQuestMenu(Student student) {
        UsersView view = new UsersView();
        List<Quest> quests = ModelDaoFactory.getByType(QuestDAO.class).getAllModels();
        view.displayObjects(quests);
        int pickedId = view.getNotNegativeNumberFromUser("Choose id to pick quest: ");
        Quest pickedQuest = quests.stream()
                .filter(q -> q.getId() == pickedId)
                .findAny()
                .orElse(null);
        view.clearScreen();
        if (pickedQuest != null) {
            if (!student.getStudentsQuests().containsQuest(pickedQuest)) {
                student.getStudentsQuests().addItem(pickedQuest);
                view.displayMessageInNextLine("You have taken up the task:\n");
                view.displayObject(pickedQuest);
            } else {
                view.displayMessageInNextLine("You already have this quest!");
            }
        } else {
            view.displayMessage("There's no quest with such id..");
        }
    }
}
