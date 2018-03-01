package factory;

import dao.*;

public class DaoFactory {

    @SuppressWarnings("unchecked")
    public static <T extends CommonModelDAO> T get(String objectTypeName) {

        CommonModelDAO dao = null;

        switch (objectTypeName) {
            case ("Student"):
                dao = new StudentDAO();
                break;
            case ("Mentor"):
                dao = new MentorDAO();
                break;
            case ("Admin"):
                dao = new AdminDAO();
                break;
            case ("Group"):
                dao = new GroupDAO();
                break;
            case ("Team"):
                dao = new TeamDAO();
                break;
            case ("Quest"):
                dao = new QuestDAO();
                break;
            case ("Artifact"):
                dao = new ArtifactDAO();
                break;
            case ("Attendance"):
                dao = new AttendanceDAO();
                break;
            case ("ExperienceLevels"):
                dao = new ExperienceLevelsDAO();
                break;
            case ("StudentInventory"):
                dao = new StudentInventoryDAO();
                break;
            case ("TeamInventory"):
                dao = new TeamInventoryDAO();
                break;
            case ("StudentsQuests"):
                dao = new StudentsQuestsDAO();
                break;
        }
        return (T) dao;
    }
}
