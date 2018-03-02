package dao;

import managers.DbManager;
import managers.DbManagerImpl;

import java.sql.Connection;

public class DaoFactory {

    private static Connection connection;
    private static DbManager dbManager = new DbManagerImpl();

    @SuppressWarnings("unchecked")
    public static <T extends CommonModelDAO> T getByModel(String objectTypeName) {

        connection = dbManager.getConnection();
        CommonModelDAO dao = null;

        switch (objectTypeName) {
            case ("Student"):
                dao = new StudentDAO(connection);
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

    @SuppressWarnings("unchecked")
    public static <T extends CommonModelDAO> T getByType(Class<T> type) {

        connection = dbManager.getConnection();
        String daoName = type.getSimpleName();
        CommonModelDAO dao = null;

        switch (daoName) {
            case ("StudentDAO"):
                dao = new StudentDAO(connection);
                break;
            case ("MentorDAO"):
                dao = new MentorDAO();
                break;
            case ("AdminDAO"):
                dao = new AdminDAO();
                break;
            case ("GroupDAO"):
                dao = new GroupDAO();
                break;
            case ("TeamDAO"):
                dao = new TeamDAO();
                break;
            case ("QuestDAO"):
                dao = new QuestDAO();
                break;
            case ("ArtifactDAO"):
                dao = new ArtifactDAO();
                break;
            case ("AttendanceDAO"):
                dao = new AttendanceDAO();
                break;
            case ("ExperienceLevelsDAO"):
                dao = new ExperienceLevelsDAO();
                break;
            case ("StudentInventoryDAO"):
                dao = new StudentInventoryDAO();
                break;
            case ("TeamInventoryDAO"):
                dao = new TeamInventoryDAO();
                break;
            case ("StudentsQuestsDAO"):
                dao = new StudentsQuestsDAO();
                break;
        }
        return (T) dao;
    }

    public static LoginDAO getLoginDAO() {

        connection = dbManager.getConnection();

        return new LoginDAOImpl(connection);
    }
}
