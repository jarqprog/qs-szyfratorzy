package school;

import application.DataTool;
import application.DbManagerDAO;
import application.FilePath;

import java.util.List;

public class SchoolController {

    private SchoolModel school;
    private SchoolView view;
    private boolean shouldExit = false;


    public SchoolController(SchoolModel school) {
        this.school = school;
        this.view = new SchoolView();
    }

    public SchoolController() {
        this.school = new SchoolModel();
        this.view = new SchoolView();
    }

    public List<GroupModel> getGroups() {
        return school.getGroups();
    }

    public List<TeamModel> getTeams() {
        return school.getTeams();
    }

    public ExperienceLevels getExperienceLevels() {
        return school.getExperienceLevels();
    }

    public void manageExperienceLevels() {
        while(! shouldExit) {
            view.clearScreen();
            view.displayExpLevelsManager();
            String[] correctChoices = {"1", "2", "3", "4", "5", "0"};
            String userChoice = getUserChoice(correctChoices);
            switch (userChoice) {
                case "1":
                    showExperienceLevels();
                    break;
                case "2":
                    restoreDefaultExpLvls();
                    break;
                case "3":
                    executeNotImplementedInfo();
                    break;
                case "4":
                    executeNotImplementedInfo();
                    break;
                case "5":
                    executeNotImplementedInfo();
                    break;
                case "0":
                    shouldExit = true;
                    break;
            }
            if(! shouldExit){
                view.handlePause();
            }
        }
        shouldExit = false;
    }

    private String getUserChoice(String[] correctChoices) {
        String userChoice = "";
        Boolean isChoiceReady = false;
        while (!isChoiceReady) {
            userChoice = view.getUserInput("Select an option: ");
            isChoiceReady = DataTool.checkIfElementInArray(correctChoices, userChoice);
        }
        return userChoice;
    }

    private void restoreDefaultExpLvls() {
        importExpLvlFromSql();
        view.clearScreen();
        view.displayMessage("Experience levels restored:");
        showExperienceLevels();
    }

    private void showExperienceLevels() {
        String expToDisplay = getExperienceLevels().toString();
        view.displayMessage(expToDisplay);
    }

    public void importExpLvlFromSql() {
        DbManagerDAO dao = new DbManagerDAO();
        String sqlFilePath = FilePath.UPDATE_EXP_LVL.getPath();
        dao.updateDatabase(sqlFilePath);
    }

    public void importExpLvlFromSql(String sqlFilePath) {
        DbManagerDAO dao = new DbManagerDAO();
        dao.updateDatabase(sqlFilePath);
    }

    public SchoolModel getSchool(){
        return school;
    }

    protected void executeNotImplementedInfo() {
        view.displayMessage("Not implemented yet");
        view.handlePause();
    }

}
