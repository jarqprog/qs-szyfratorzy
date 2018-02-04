package school;

import application.DataTool;
import application.DbManagerDAO;
import application.FilePath;
import java.util.List;

public class SchoolController {

    private SchoolModel school;
    private SchoolView view;
    private SchoolDAO dao;
    private ExperienceLevels experienceLevels;
    private boolean shouldExit = false;

    public SchoolController() {
        this.school = new SchoolModel();
        this.view = new SchoolView();
        this.dao = new SchoolDAO();
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
                    restoreDefaultExpLevels();
                    break;
                case "3":
                    modifyExperienceLevels();
                    break;
                case "4":
                    clearChosenExperienceLevel();
                    break;
                case "5":
                    clearAllExperienceLevels();
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

    private void showExperienceLevels() {
        view.displayObject(getExperienceLevels());
    }

    private void restoreDefaultExpLevels() {
        importExpLvlFromSql();
        view.clearScreen();
        view.displayMessage("Restored:");
        showExperienceLevels();
    }

    private void modifyExperienceLevels() {
        experienceLevels = school.getExperienceLevels();
        String levelName;
        view.clearScreen();
        view.displayMessage("Current state:");
        view.displayObject(experienceLevels);
        levelName = view.getUserInput(" Type:\n\n - new level name to add level\n" +
                " - or existing level name to modify level\n" +
                " - or press '0' to quit process ---> ");
        if(! levelName.equals("0")){
            int levelValue = view.getNotNegativeNumberFromUser(" - type required experience level ---> ");
            experienceLevels.addLevel(levelName, levelValue);
            view.clearScreen();
            view.displayMessage("Added:");
            showExperienceLevels();
        }
    }

    private void clearAllExperienceLevels() {
        experienceLevels = school.getExperienceLevels();
        experienceLevels.clearLevels();
        view.clearScreen();
        view.displayMessage("Cleared:");
        showExperienceLevels();
    }

    private void clearChosenExperienceLevel() {
        experienceLevels = school.getExperienceLevels();
        view.clearScreen();
        boolean isDone = false;
        String levelName = "";
        while(! isDone && ! levelName.equals("0")){
            view.clearScreen();
            view.displayMessage("Current state:");
            view.displayObject(experienceLevels);
            levelName = view.getUserInput(" Type:\n\n" +
                    " - existing level name to modify level\n" +
                    " - or press '0' to quit process ---> ");
            isDone = experienceLevels.containsGivenLevel(levelName);
            if(! isDone && ! levelName.equals("0")){
                view.displayMessage("   - You should type existing level name.");
            }
        }
        if (! levelName.equals("0")) {
            view.clearScreen();
            experienceLevels.removeLevel(levelName);
            view.displayMessage("Cleared:");
            showExperienceLevels();
        }
    }

    private void importExpLvlFromSql() {
        DbManagerDAO dao = new DbManagerDAO();
        String sqlFilePath = FilePath.UPDATE_EXP_LVL.getPath();
        dao.updateDatabase(sqlFilePath);
    }

    public SchoolModel getSchool(){
        return school;
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

    protected void executeNotImplementedInfo() {
        view.displayMessage("Not implemented yet");
        view.handlePause();
    }
}
