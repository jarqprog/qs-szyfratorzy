package users;

import item.ArtifactModel;

import java.util.List;
import item.ArtifactDAO;

public abstract class  UserController{

    public abstract void handleMainMenu();

    public void showUsers(String[] users){
        UsersView view = new UsersView();
        view.displayUsers(users);

    }

    protected Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }

    protected List<MentorModel> getMentors(){
        MentorDAO dao = new MentorDAO();
        return dao.getManyObjects("SELECT * FROM mentors;");
    }

    protected List<ArtifactModel> getArtifacts(){
        ArtifactDAO dao = new ArtifactDAO();
        return dao.getManyObjects("SELECT * FROM artifacts;");
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
}
