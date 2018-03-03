package dao;

import factory.ConnectionFactory;

import java.sql.Connection;

public class DaoFactory {

    private static Connection connection;

    @SuppressWarnings("unchecked")
    public static <T extends CommonModelDAO> T getByModel(String objectTypeName) {

        connection = ConnectionFactory.getConnection();
        CommonModelDAO dao = null;

        switch (objectTypeName) {
            case ("Student"):
                dao = new StudentDAO(connection);
                break;
            case ("Mentor"):
                dao = new MentorDAO(connection);
                break;
            case ("Admin"):
                dao = new AdminDAO(connection);
                break;
            case ("Group"):
                dao = new GroupDAO(connection);
                break;
            case ("Team"):
                dao = new TeamDAO(connection);
                break;
            case ("Quest"):
                dao = new QuestDAO(connection);
                break;
            case ("Artifact"):
                dao = new ArtifactDAO(connection);
                break;
            case ("Attendance"):
                dao = new AttendanceDAO(connection);
                break;
            case ("ExperienceLevels"):
                dao = new ExperienceLevelsDAO(connection);
                break;
            case ("StudentInventory"):
                dao = new StudentInventoryDAO(connection);
                break;
            case ("TeamInventory"):
                dao = new TeamInventoryDAO(connection);
                break;
            case ("StudentsQuests"):
                dao = new StudentsQuestsDAO(connection);
                break;
        }
        return (T) dao;
    }

    @SuppressWarnings("unchecked")
    public static <T extends CommonModelDAO> T getByType(Class<T> type) {

        connection = ConnectionFactory.getConnection();
        String daoName = type.getSimpleName();
        CommonModelDAO dao = null;

        switch (daoName) {
            case ("StudentDAO"):
                dao = new StudentDAO(connection);
                break;
            case ("MentorDAO"):
                dao = new MentorDAO(connection);
                break;
            case ("AdminDAO"):
                dao = new AdminDAO(connection);
                break;
            case ("GroupDAO"):
                dao = new GroupDAO(connection);
                break;
            case ("TeamDAO"):
                dao = new TeamDAO(connection);
                break;
            case ("QuestDAO"):
                dao = new QuestDAO(connection);
                break;
            case ("ArtifactDAO"):
                dao = new ArtifactDAO(connection);
                break;
            case ("AttendanceDAO"):
                dao = new AttendanceDAO(connection);
                break;
            case ("ExperienceLevelsDAO"):
                dao = new ExperienceLevelsDAO(connection);
                break;
            case ("StudentInventoryDAO"):
                dao = new StudentInventoryDAO(connection);
                break;
            case ("TeamInventoryDAO"):
                dao = new TeamInventoryDAO(connection);
                break;
            case ("StudentsQuestsDAO"):
                dao = new StudentsQuestsDAO(connection);
                break;
        }
        return (T) dao;
    }

    public static SchoolDAO getSchoolDAO() {
        return new SchoolDAOImpl(ConnectionFactory.getConnection());
    }

    public static ShopDAO getShopDAO() {
        return new ShopDAOImpl(ConnectionFactory.getConnection());
    }

    public static LoginDAO getLoginDAO() {
        return new LoginDAOImpl(ConnectionFactory.getConnection());
    }
}
