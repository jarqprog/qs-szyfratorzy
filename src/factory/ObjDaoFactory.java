package factory;

import dao.*;
import model.AbsObject;

public class ObjDaoFactory {

    @SuppressWarnings("unchecked")
    public static <T extends AbsObjDAO, E extends AbsObject> T get(Class<E> type) {

        T dao = null;

        String typeName = type.getClass().getSimpleName();
        switch (typeName) {
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
        }
        return dao;
    }
}
