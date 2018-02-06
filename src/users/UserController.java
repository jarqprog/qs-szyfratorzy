package users;


import java.util.List;
import item.ArtifactDAO;
import item.ArtifactModel;
import item.QuestDAO;
import item.QuestModel;

public abstract class  UserController{

    public abstract void handleMainMenu();

    protected void showUsers(String[] users){
        UsersView view = new UsersView();
        view.displayUsers(users);

    }

    protected <T extends UserModel> String showProfile(T user){
        UsersView view = new UsersView();
       return view.displayUserWithDetails(user);
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

    protected <T> String[] prepareObjectsToDisplay(List<T> objects) {
        String[] objectsToDisplay = new String[objects.size()];
        int index = 0;
        for(T object : objects){
            objectsToDisplay[index] = object.toString();
            index ++;
        }
        return objectsToDisplay;
    }
}
