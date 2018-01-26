package users;

import java.util.List;

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

    protected void executeNotImplementedInfo() {
        UsersView view = new UsersView();
        view.clearScreen();
        view.displayMessage("Not implemented yet");
        view.handlePause();
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
}
