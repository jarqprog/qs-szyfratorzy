package dao;

import factory.ConnectionFactory;

import java.sql.Connection;

public class SpecialDaoFactory {

    public static <T extends SpecialDAO> T getByType(Class<T> type) {

        Connection connection = ConnectionFactory.getConnection();
        String daoName = type.getSimpleName();
        SpecialDAO dao = null;

        switch (daoName) {
            case ("SchoolDAO"):
                dao = new SchoolDAOImpl(connection);
                break;
            case ("ShopDAO"):
                dao = new ShopDAOImpl(connection);
                break;
            case ("LoginDAO"):
                dao = new LoginDAOImpl(connection);
                break;
        }
        return type.cast(dao);
    }
}
