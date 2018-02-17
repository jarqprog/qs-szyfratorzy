package factory;

public class AbsFactory {

    @SuppressWarnings("unchecked")
    public static <T extends Factory> T get(Class<T> type) {

        T factory = null;
        String factoryName = type.getSimpleName();

        switch(factoryName) {

            case("AdminFactoryImpl"):
                factory = (T) new AdminFactoryImpl();
                break;
            case("StudentFactoryImpl"):
                factory = (T) new StudentFactoryImpl();
                break;
            case("MentorFactoryImpl"):
                factory = (T) new MentorFactoryImpl();
                break;
            case("GroupFactoryImpl"):
                factory = (T) new GroupFactoryImpl();
                break;
            case("TeamFactoryImpl"):
                factory = (T) new TeamFactoryImpl();
                break;
        }
        return factory;
    }
}