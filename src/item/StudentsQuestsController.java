package item;



import java.util.List;

import application.CreatableDAO;
import dao.QuestDAO;


public class StudentsQuestsController {

    QuestDAO questDAO;
    QuestView questView;
    CreatableDAO dao;


    public void addQuestToStudentsQuests(StudentsQuestsModel studentsQuests){

        questDAO = new QuestDAO();
        questView = new QuestView();
        dao = new QuestDAO();

        List<QuestModel> quests= questDAO.getAllObjects();
        questView.displayQuests(quests);
        Integer questsId = questView.typeQuestIdInput();
        QuestModel pickedQuest = dao.getObjectById(questsId);
        studentsQuests.addItem(pickedQuest);




        }
    }
