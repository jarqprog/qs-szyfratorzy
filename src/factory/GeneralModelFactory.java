package factory;

import model.*;

public class GeneralModelFactory {

    @SuppressWarnings("unchecked")
    public static <T extends ModelFactory> T get(Class<T> type) {

        ModelFactory factory = null;
        String factoryName = type.getSimpleName();

        switch(factoryName) {

            case("AdminFactoryImpl"):
                factory = new AdminFactoryImpl();
                break;
            case("StudentFactoryImpl"):
                factory = new StudentFactoryImpl();
                break;
            case("MentorFactoryImpl"):
                factory = new MentorFactoryImpl();
                break;
            case("GroupFactoryImpl"):
                factory = new GroupFactoryImpl();
                break;
            case("TeamFactoryImpl"):
                factory = new TeamFactoryImpl();
                break;
            case("QuestFactoryImpl"):
                factory = new QuestFactoryImpl();
                break;
            case("ArtifactFactoryImpl"):
                factory = new ArtifactFactoryImpl();
                break;
            case("ExpLevelsFactoryImpl"):
                factory = new ExpLevelsFactoryImpl();
                break;
        }
        return (T) factory;
    }
}