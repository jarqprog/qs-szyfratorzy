package users;


import java.util.List;
import item.ArtifactDAO;
import item.ArtifactModel;
import item.QuestDAO;
import item.QuestModel;

public abstract class  UserController{

    public abstract void handleMainMenu();

    public void showUsers(String[] users){
        UsersView view = new UsersView();
        view.displayUsers(users);

    }

    protected List<MentorModel> getMentors(){
        MentorDAO dao = new MentorDAO();
        return dao.getManyObjects("SELECT * FROM mentors;");
    }

    protected List<ArtifactModel> getArtifacts(){
        ArtifactDAO dao = new ArtifactDAO();
        return dao.getManyObjects("SELECT * FROM artifacts;");
    }

    protected List<QuestModel> getQuests(){
        QuestDAO dao = new QuestDAO();
        return dao.getManyObjects("SELECT * FROM quests;");
    }

    protected void executeNotImplementedInfo() {
        UsersView view = new UsersView();
        view.displayMessage("Not implemented yet");
    }

    protected String[] prepareMentorsToDisplay(List<MentorModel> mentors) {
        String[] mentorsToDisplay = new String[mentors.size()];
        int index = 0;
        for(MentorModel mentor : mentors){
            mentorsToDisplay[index] = mentor.toString();
            index ++;
        }
        return mentorsToDisplay;
    }

    protected String[] prepareArtifactsToDisplay(List<ArtifactModel> artifacts){
        String[] artifactsToDisplay = new String[artifacts.size()];
        int index = 0;
        for(ArtifactModel artifact : artifacts){
            artifactsToDisplay[index] = artifact.toString();
            index ++;
        }
        return artifactsToDisplay;
    }

    protected String[] prepareQuestsToDisplay(List<QuestModel> quests){
        String[] questsToDisplay = new String[quests.size()];
        int index = 0;
        for(QuestModel quest : quests){
            questsToDisplay[index] = quest.toString();
            index ++;
        }
        return questsToDisplay;
    }
}
