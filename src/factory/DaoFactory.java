package factory;

import dao.*;
import model.AbsObject;
import view.UsersView;

public class DaoFactory {

    @SuppressWarnings("unchecked")
    public static <T extends AbsObjDAO, E extends AbsObject> T get(String objectTypeName) {

        T dao = null;

//        String typeName = type.getClass().getSimpleName();

        UsersView view = new UsersView();
        view.displayMessage("Jestem w frabryce dao, próbuję zapisać: " + objectTypeName);
        view.handlePause();


        switch (objectTypeName) {
            case ("Student"):
                dao = (T) new StudentDAO();
                break;
            case ("Mentor"):
                dao = (T) new MentorDAO();
                break;
            case ("Admin"):
                dao = (T) new AdminDAO();
                break;
            case ("Group"):
                dao = (T) new GroupDAO();
                break;
            case ("Team"):
                dao = (T) new TeamDAO();
                break;
            case ("Quest"):
                dao = (T) new QuestDAO();
                break;
            case ("Artifact"):
                dao = (T) new ArtifactDAO();
                break;
            case ("Attendance"):
                dao = (T) new AttendanceDAO();
                break;
            case ("ExperienceLevels"):
                dao = (T) new ExperienceLevelsDAO();
                break;
            case ("StudentInventory"):
                dao = (T) new StudentInventoryDAO();
                break;
            case ("TeamInventory"):
                dao = (T) new TeamInventoryDAO();
                break;
        }
        return dao;
    }
}
